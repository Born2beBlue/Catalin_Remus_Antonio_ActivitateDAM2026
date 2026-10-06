package com.example.catalin_remus_antonio_dam2026;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Seminar", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("Seminar", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Seminar", "onDestroy");
        Log.d("Seminar", "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Seminar", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("Seminar", "onResume");
    }


}