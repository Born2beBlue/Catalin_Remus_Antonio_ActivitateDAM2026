package com.example.seminar4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ActivitateListaImagini extends AppCompatActivity {

    private final List<ImaginiDomeniu> lista = new ArrayList<>();
    private ImageAdapter adapter;
    private final List<String> linkuriImagini = new ArrayList<>();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lista_imagini), (v, insets) -> {
            Insets s = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(s.left, s.top, s.right, s.bottom);
            return insets;
        });

        linkuriImagini.add("https://lcdn.altex.ro/resize/media/catalog/product/a/3/16fa6a9aef7ffd6209d5fd9338ffa0b1/a31_plus_blk_3d_1_915783ef_001_b445df1b.jpg");
        linkuriImagini.add("https://lcdn.altex.ro/resize/media/catalog/product/l/e/16fa6a9aef7ffd6209d5fd9338ffa0b1/legion_t5_30agb10_ct1_01_d7a363f6.jpg");
        linkuriImagini.add("https://lcdn.altex.ro/resize/media/catalog/product/0/1/16fa6a9aef7ffd6209d5fd9338ffa0b1/01_tuf_dt_t500_l_59f2ef59.jpg");
        linkuriImagini.add("https://lcdn.altex.ro/resize/media/catalog/product/l/a/16fa6a9aef7ffd6209d5fd9338ffa0b1/lapfx707vvhx033_2_a073e35e.jpg");
        linkuriImagini.add("https://lcdn.altex.ro/resize/media/catalog/product/i/d/16fa6a9aef7ffd6209d5fd9338ffa0b1/ideapad_slim_3_15abr8_ct2_10_67dee325.jpg");

        ListView lv = findViewById(R.id.lv_imagini);

        adapter = new ImageAdapter(lista, this, R.layout.row_imagini);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener((parent, view, position, id) -> {
            Intent it = new Intent(this, WebViewActivity.class);
            it.putExtra("link", lista.get(position).getLink());
            startActivity(it);
        });

        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            List<ImaginiDomeniu> rezultat = new ArrayList<>();
            for (int i = 0; i < linkuriImagini.size(); i++) {
                String link = linkuriImagini.get(i);
                HttpURLConnection con = null;
                try {
                    con = (HttpURLConnection) new URL(link).openConnection();
                    con.setConnectTimeout(10000);
                    con.setReadTimeout(10000);
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    InputStream is = con.getInputStream();
                    Bitmap bmp = BitmapFactory.decodeStream(is);
                    is.close();
                    if (bmp != null) {
                        rezultat.add(new ImaginiDomeniu(bmp, link, "PC " + (i + 1)));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (con != null) con.disconnect();
                }
            }

            handler.post(() -> {
                lista.clear();
                lista.addAll(rezultat);
                adapter.notifyDataSetChanged();
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}