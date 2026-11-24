package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivitateAdaugare extends AppCompatActivity {

    protected int getLayoutId() {
        return R.layout.activity_main_s4;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(getLayoutId());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets s = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(s.left, s.top, s.right, s.bottom);
            return insets;
        });

        Spinner spOs = findViewById(R.id.sp_os);
        String[] sisteme = {"Windows 11", "Windows 10", "Linux", "macOS"};
        spOs.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, sisteme));
    }

    public void salveaza(View view) {
        EditText etMarca = findViewById(R.id.et_marca);
        EditText etPret = findViewById(R.id.et_pret);
        CompoundButton cbSsd = findViewById(R.id.cb_ssd);
        RadioGroup rgTip = findViewById(R.id.rg_tip);
        Spinner spOs = findViewById(R.id.sp_os);
        RatingBar rbRating = findViewById(R.id.rb_rating);
        CompoundButton swPlaca = findViewById(R.id.sw_placa_video);

        String marca = etMarca.getText().toString().trim();
        if (marca.isEmpty()) {
            etMarca.setError("Introdu marca");
            return;
        }
        double pret;
        try {
            pret = Double.parseDouble(etPret.getText().toString());
        } catch (NumberFormatException e) {
            etPret.setError("Introdu un preț valid");
            return;
        }

        String tip = rgTip.getCheckedRadioButtonId() == R.id.rb_desktop ? "Desktop" : "Laptop";

        Computer pc = new Computer(marca, pret, cbSsd.isChecked(), tip,
                spOs.getSelectedItem().toString(), rbRating.getRating(),
                swPlaca.isChecked());

        Intent it = new Intent();
        it.putExtra("computer", pc);
        setResult(RESULT_OK, it);
        finish();
    }
}