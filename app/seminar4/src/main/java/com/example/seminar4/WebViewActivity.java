package com.example.seminar4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class WebViewActivity extends AppCompatActivity {
    private static final int COD_ADAUGARE = 100;
    private static final int COD_LISTA = 101;
    private static final int COD_LISTA_IMAGINI = 102;

    private List<ImaginiDomeniu> lista = null;
    private int idModificat = 0;
    private ImageAdapter adapter = null;

    List<String> linkuriImagini = new ArrayList<>();
    List<Bitmap> imagini = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.web_view_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.web_view), (v, insets) -> {
            Insets s = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(s.left, s.top, s.right, s.bottom);
            return insets;
        });

        Intent it = getIntent();
        String link = it.getStringExtra("link");

        WebView wv = findViewById(R.id.web_view_computer);
        wv.loadUrl(link);

    }
}
