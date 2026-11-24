package com.example.seminar5;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VremeOras extends AppCompatActivity {

    private static final String API = "";

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    private EditText editTextOras;
    private Spinner spinnerZile;
    private TextView textViewRezultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.vreme_oras);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.vreme_oras), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextOras = findViewById(R.id.edittext_oras);
        spinnerZile = findViewById(R.id.spinner_zile);
        textViewRezultat = findViewById(R.id.textview_rezultat);
    }

    public void getCodOras(View view) {
        final String numeOras = editTextOras.getText().toString().trim();
        if (numeOras.isEmpty()) {
            Toast.makeText(this, "Introdu un oras", Toast.LENGTH_SHORT).show();
            return;
        }

        String metoda;

        if(spinnerZile.getSelectedItemPosition() == 0){
            metoda = "1day";
        }
        else{
            metoda = "5day";
        }
        executor.execute(new Runnable() {
            @Override
            public void run() {
                String rezultat;
                try {
                    String linkOras = "https://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + API + "&q=" + numeOras.toString();
                    String raspunsOras = citesteDeLaLink(linkOras);

                    JSONArray vector = new JSONArray(raspunsOras);
                    if (vector.length() == 0) {
                        rezultat = "Orasul nu a fost gasit";
                    } else {
                        JSONObject obiect = vector.getJSONObject(0);
                        String cheieOras = obiect.getString("Key");

                        String linkVreme = "https://dataservice.accuweather.com/forecasts/v1/daily/" + metoda + "/" + cheieOras + "?apikey=" + API + "&metric=true";
                        String raspunsVreme = citesteDeLaLink(linkVreme);

                        JSONObject radacina = new JSONObject(raspunsVreme);
                        JSONArray zile = radacina.getJSONArray("DailyForecasts");

                        StringBuilder sb = new StringBuilder();
                        sb.append("Cod oras: ").append(cheieOras).append("\n\n");
                        for (int i = 0; i < zile.length(); i++) {
                            JSONObject zi = zile.getJSONObject(i);
                            String data = zi.getString("Date").substring(0, 10);
                            JSONObject temp = zi.getJSONObject("Temperature");
                            double min = temp.getJSONObject("Minimum").getDouble("Value");
                            double max = temp.getJSONObject("Maximum").getDouble("Value");
                            sb.append(data).append(": min ").append(min).append("°C").append(", max ").append(max).append("°C\n");
                        }
                        rezultat = sb.toString();
                    }
                } catch (Exception e) {
                    rezultat = "Eroare: " + e.getMessage();
                }

                final String text = rezultat;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textViewRezultat.setText(text);
                    }
                });
            }
        });
    }

    private String citesteDeLaLink(String link) throws Exception {
        URL url = new URL(link);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try {
            int cod = conn.getResponseCode();
            if (cod != 200) {
                throw new Exception("HTTP " + cod);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String linie;
            while ((linie = reader.readLine()) != null) {
                builder.append(linie);
            }
            reader.close();
            return builder.toString();
        } finally {
            conn.disconnect();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}