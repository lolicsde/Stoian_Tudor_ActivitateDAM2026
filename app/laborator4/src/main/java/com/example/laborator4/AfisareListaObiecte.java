package com.example.laborator4;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AfisareListaObiecte extends AppCompatActivity {

    private ArrayList<StoianTudor> lista_primita;
    private ActivityResultLauncher<Intent> modificare_date_tastatura;

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

        modificare_date_tastatura = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null){
                        Intent data = result.getData();

                        StoianTudor obiect_modificat = null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            obiect_modificat = (StoianTudor) data.getParcelableExtra("obiect_modificat", StoianTudor.class);
                        }
                        int pozitie = data.getIntExtra("pozitie", -1);

                        if(obiect_modificat != null && pozitie != -1){
                            lista_primita.set(pozitie, obiect_modificat);
                            adaptor.notifyDataSetChanged();
                        }
                    }
                }
        );

        lista_obiecte.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), CompletareDateTastatura.class);

                it.putExtra("sursa", "AfisareListaObiecte");
                it.putExtra("obiect_selectat", lista_primita.get(position));
                it.putExtra("pozitie", position);

                modificare_date_tastatura.launch(it);
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