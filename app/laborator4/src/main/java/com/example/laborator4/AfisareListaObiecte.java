package com.example.laborator4;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AfisareListaObiecte extends AppCompatActivity {

    private ArrayList<StoianTudor> lista_primita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afisare_lista_obiecte);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            lista_primita = getIntent().getParcelableArrayListExtra("lista_tastaturi", StoianTudor.class);
        }

        AdaptorTastaturi adaptor = new AdaptorTastaturi(getApplicationContext(), lista_primita);

//        ArrayAdapter<StoianTudor> adapter = new ArrayAdapter<>(
//                getApplicationContext(),
//                android.R.layout.simple_list_item_1,
//                lista_primita
//        );

        ListView lista_obiecte = findViewById(R.id.lv_lista_obiecte);
        lista_obiecte.setAdapter(adaptor);

        lista_obiecte.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), CompletareDateTastatura.class);

//                Toast.makeText(getApplicationContext(), lista_primita.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

//        lista_obiecte.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                adapter.remove(lista_primita.get(position));
//                adapter.notifyDataSetChanged();
//                return true;
//            }
//        });
    }
}