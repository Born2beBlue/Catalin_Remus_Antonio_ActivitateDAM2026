package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity_S4 extends AppCompatActivity {
    private static final int COD_ADAUGARE = 100;
    private static final int COD_LISTA = 101;

    private TextView tvRezultat;
    private ArrayList<Computer> listaComputere = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_s4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets s = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(s.left, s.top, s.right, s.bottom);
            return insets;
        });
        tvRezultat = findViewById(R.id.tv_rezultat);
    }

    public void deschideAdaugare(View view) {
        startActivityForResult(new Intent(this, ActivitateAdaugare.class), COD_ADAUGARE);
    }

    public void deschideAdaugareAI(View view) {
        startActivityForResult(new Intent(this, ActivitateAdaugareAI.class), COD_ADAUGARE);
    }

    public void deschideLista(View view) {
        Intent it = new Intent(this, ActivitateLista.class);
        it.putParcelableArrayListExtra("lista", listaComputere);
        startActivityForResult(it, COD_LISTA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) return;

        if (requestCode == COD_ADAUGARE) {
            Computer pc = IntentCompat.getParcelableExtra(data, "computer", Computer.class);
            if (pc != null) {
                listaComputere.add(pc);
                Toast.makeText(this, "Adăugat:\n" + pc, Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == COD_LISTA) {
            ArrayList<Computer> modificata =
                    IntentCompat.getParcelableArrayListExtra(data, "lista", Computer.class);
            if (modificata != null) {
                listaComputere = modificata;
            }
        }
        tvRezultat.setText("Computere în listă: " + listaComputere.size());
    }
}