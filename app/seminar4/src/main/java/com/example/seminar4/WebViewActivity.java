package com.example.seminar4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

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
