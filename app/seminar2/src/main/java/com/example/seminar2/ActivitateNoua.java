package com.example.seminar2;

import android.app.ComponentCaller;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivitateNoua extends AppCompatActivity {

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
    public void deschideActivitate(View view){
        Intent it=new Intent(this, ActivitateRezultat.class);
        Bundle bundle= new Bundle();
        bundle.putInt("numar1", 6);
        bundle.putInt("numar2", 9);
        bundle.putString("mesaj", "Mesaj transmis din Prima activitate");
        it.putExtras(bundle);
        startActivityForResult(it, 37);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data, @NonNull ComponentCaller caller) {
        super.onActivityResult(requestCode, resultCode, data, caller);
        if(requestCode==37){
            if(resultCode==RESULT_OK){
                String mesaj=data.getStringExtra("mesaj");
                int suma = data.getIntExtra("suma", 0);
                Toast.makeText(this, mesaj+" "+suma, Toast.LENGTH_LONG).show();
            }
        }
    }
}