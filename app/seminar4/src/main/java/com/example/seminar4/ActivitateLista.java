package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ActivitateLista extends AppCompatActivity {
    private ArrayList<Computer> lista;
    private ArrayAdapter<Computer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets s = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(s.left, s.top, s.right, s.bottom);
            return insets;
        });

        lista = IntentCompat.getParcelableArrayListExtra(getIntent(), "lista", Computer.class);
        if (lista == null) lista = new ArrayList<>();

        ListView lv = findViewById(R.id.lv_computere);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(this, lista.get(position).toString(), Toast.LENGTH_LONG).show());

        lv.setOnItemLongClickListener((parent, view, position, id) -> {
            lista.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Computer șters", Toast.LENGTH_SHORT).show();
            return true;
        });

        trimiteRezultat();
    }

    private void trimiteRezultat() {
        Intent it = new Intent();
        it.putParcelableArrayListExtra("lista", lista);
        setResult(RESULT_OK, it);
    }

    @Override
    public void finish() {
        trimiteRezultat();
        super.finish();
    }
}