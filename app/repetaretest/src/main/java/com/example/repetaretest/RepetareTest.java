package com.example.repetaretest;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class RepetareTest extends AppCompatActivity {

    private List<Pisica> pisici = new ArrayList<>();
    private TextView tv_afisareUltimaPisica;
    private Button bttn_catreAdaugarePisica;
    private ActivityResultLauncher<Intent> lansareAdaugarePisica;
    private Button bttn_catreListaPisici;
    private ActivityResultLauncher<Intent> lansareListaPisici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_repetare_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv_afisareUltimaPisica = findViewById(R.id.tv_dateleUltimeiPisici);
        bttn_catreAdaugarePisica = findViewById(R.id.bttn_catreAdaugarePisica);

        lansareAdaugarePisica = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result->{
                    if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null){
                        Intent data = result.getData();

                        String rasa = data.getStringExtra("rasa");
                        String culoare = data.getStringExtra("culoare");
                        Float greutate = data.getFloatExtra("greutate", -1);
                        Boolean arePedigree = data.getBooleanExtra("arePedigree", false);
                        Boolean esteVaccinata = data.getBooleanExtra("esteVaccinata", false);
                        Double pret = data.getDoubleExtra("pret", -1);

                        Pisica pisica = new Pisica(rasa, culoare, greutate, arePedigree, esteVaccinata, pret);
                        pisici.add(pisica);
                        tv_afisareUltimaPisica.setText(pisica.toString());
                    }
                });

        bttn_catreAdaugarePisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), CompletareDate.class);
                it.putExtra("sursa", "RepetareTest");
                lansareAdaugarePisica.launch(it);
            }
        });

        lansareListaPisici = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result->{
                    if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null){
                        Intent data = result.getData();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            pisici = data.getParcelableArrayListExtra("pisici", Pisica.class);
                        }
                    }
                });

        bttn_catreListaPisici = findViewById(R.id.bttn_catreListaPisici);
        bttn_catreListaPisici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ListaPisici.class);
                it.putParcelableArrayListExtra("pisici", (ArrayList<Pisica>) pisici);
                lansareListaPisici.launch(it);
            }
        });
    }
}