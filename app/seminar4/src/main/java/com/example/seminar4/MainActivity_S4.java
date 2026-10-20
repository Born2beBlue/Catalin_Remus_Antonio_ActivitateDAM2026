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

public class MainActivity_S4 extends AppCompatActivity {
    private static final int COD_ADAUGARE = 100;
    private TextView tvRezultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_s4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COD_ADAUGARE && resultCode == RESULT_OK && data != null) {
            Computer pc = IntentCompat.getParcelableExtra(data, "computer", Computer.class);
            if (pc != null) {
                tvRezultat.setText(pc.toString());
                Toast.makeText(this, pc.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}