package com.example.repetaretest;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ListaPisici extends AppCompatActivity {

    private List<Pisica> pisici;
    private ListView listaPisici;
    private ActivityResultLauncher<Intent> lansareCompletareDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_pisici);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pisici = getIntent().getParcelableArrayListExtra("pisici", Pisica.class);
        }

        AdaptorPisici adaptor = new AdaptorPisici(getApplicationContext(), pisici);

        listaPisici = findViewById(R.id.lv_listaPisici);
        listaPisici.setAdapter(adaptor);

        lansareCompletareDate = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result->{
                    if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null){
                        Intent data = result.getData();

                        Pisica pisica = null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            pisica = data.getParcelableExtra("pisica", Pisica.class);
                        }
                        int pozitie = data.getIntExtra("pozitie", -1);

                        pisici.set(pozitie, pisica);
                        adaptor.notifyDataSetChanged();
                    }
        });

        listaPisici.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), CompletareDate.class);
                it.putExtra("pisica", pisici.get(position));
                it.putExtra("pozitie", position);
                it.putExtra("sursa", "ListaPisici");
                lansareCompletareDate.launch(it);
            }
        });

        listaPisici.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pisici.remove(position);
                adaptor.notifyDataSetChanged();
                return true;
            }
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent it = new Intent();
                it.putParcelableArrayListExtra("pisici", (ArrayList<Pisica>) pisici);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }
}