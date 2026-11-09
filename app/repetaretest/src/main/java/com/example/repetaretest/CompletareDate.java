package com.example.repetaretest;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.slider.Slider;

public class CompletareDate extends AppCompatActivity {

    private EditText et_rasa;
    private Spinner spinner_culoare;
    private Slider slider_greutate;
    private CheckBox cb_arePedigree;
    private SwitchCompat switch_esteVaccinata;
    private EditText et_pret;
    private Button bttn_adaugaPisica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_completare_date);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_rasa = findViewById(R.id.et_rasa);
        spinner_culoare = findViewById(R.id.spinner_culoare);
        slider_greutate = findViewById(R.id.slider_greutate);
        cb_arePedigree = findViewById(R.id.cb_arePedigree);
        switch_esteVaccinata = findViewById(R.id.switch_esteVaccinata);
        et_pret = findViewById(R.id.et_pret);
        bttn_adaugaPisica = findViewById(R.id.bttn_adaugaPisica);

        ArrayAdapter<CharSequence> adaptor = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.culori_pisici)
        );
        adaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_culoare.setAdapter(adaptor);


        if(getIntent().hasExtra("sursa")){
            String sursa = getIntent().getStringExtra("sursa");
            if("RepetareTest".equals(sursa)){
                bttn_adaugaPisica.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String rasa = et_rasa.getText().toString();
                        String culoare = spinner_culoare.getSelectedItem().toString();
                        Float greutate = slider_greutate.getValue();
                        Boolean arePedigree = cb_arePedigree.isChecked();
                        Boolean esteVaccinata = switch_esteVaccinata.isChecked();
                        Double pret = Double.parseDouble(et_pret.getText().toString());

                        Intent it = new Intent();
                        it.putExtra("rasa", rasa);
                        it.putExtra("culoare", culoare);
                        it.putExtra("greutate", greutate);
                        it.putExtra("arePedigree", arePedigree);
                        it.putExtra("esteVaccinata", esteVaccinata);
                        it.putExtra("pret", pret);
                        setResult(RESULT_OK, it);
                        finish();
                    }
                });
            }
            else if("ListaPisici".equals(sursa)){
                Pisica pisica = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    pisica = getIntent().getParcelableExtra("pisica", Pisica.class);
                }
                int pozitie = getIntent().getIntExtra("pozitie", -1);

                et_rasa.setText(pisica.getRasa());
                int pozitieSpinner = adaptor.getPosition(pisica.getCuloare());
                spinner_culoare.setSelection(pozitieSpinner);
                slider_greutate.setValue(pisica.getGreutate());
                cb_arePedigree.setChecked(pisica.getArePedigree());
                switch_esteVaccinata.setChecked(pisica.getEsteVaccinata());
                et_pret.setText(pisica.getPret().toString());

                bttn_adaugaPisica.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String rasa = et_rasa.getText().toString();
                        String culoare = spinner_culoare.getSelectedItem().toString();
                        Float greutate = slider_greutate.getValue();
                        Boolean arePedigree = cb_arePedigree.isChecked();
                        Boolean esteVaccinata = switch_esteVaccinata.isChecked();
                        Double pret = Double.parseDouble(et_pret.getText().toString());

                        Pisica pisica = new Pisica(rasa, culoare, greutate, arePedigree, esteVaccinata, pret);

                        Intent it = new Intent();
                        it.putExtra("pisica", pisica);
                        it.putExtra("pozitie", pozitie);
                        setResult(RESULT_OK, it);
                        finish();
                    }
                });
            }
        }
    }
}