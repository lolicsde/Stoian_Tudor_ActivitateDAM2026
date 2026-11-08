package com.example.laborator4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<StoianTudor> listaTastaturi = new ArrayList<>();
    private Button bttn_adaugareTastatura;
    private ActivityResultLauncher<Intent> lansareAdaugareTastatura;
    private TextView tv_afisareInformatii;

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

        tv_afisareInformatii = findViewById(R.id.afisareInformatii_TV);

        lansareAdaugareTastatura = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result-> {
                    if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null){
                        Intent data = result.getData();

                        String brand = data.getStringExtra("brand");
                        String nume = data.getStringExtra("nume");
                        Boolean TK = data.getBooleanExtra("TK", false);
                        Boolean mecanica = data.getBooleanExtra("mecanica", false);
                        String culoare_led = data.getStringExtra("culoare_led");
                        String culoare_switch = data.getStringExtra("culoare_switch");

                        StoianTudor tastatura = new StoianTudor(brand, nume, TK, mecanica, culoare_led, culoare_switch);
                        listaTastaturi.add(tastatura);
                        tv_afisareInformatii.setText(tastatura.toString());
                    }
                });

        bttn_adaugareTastatura = findViewById(R.id.bttn_adaugareTastatura);
        bttn_adaugareTastatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), CompletareDateTastatura.class);
                lansareAdaugareTastatura.launch(it);
            }
        });

        Button buton_activitate_lista = findViewById(R.id.bttn_deschideActivitateLista);
        buton_activitate_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AfisareListaObiecte.class);
                it.putExtra("lista_tastaturi", (ArrayList<StoianTudor>)listaTastaturi);
                startActivity(it);
            }
        });
    }

    public void culegeDate(View view) {
        EditText et = findViewById(R.id.nume_ET);
        CheckBox cb = findViewById(R.id.eStudent_CB);

        String nume = et.getText().toString();
        Boolean eStudent = cb.isChecked();
    }
}