package com.example.laborator4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CompletareDateTastatura extends AppCompatActivity {

    private Spinner s_culoare_led;
    private RadioGroup rg_culoare_switch;
    private CheckBox cb_mecanica;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_completare_date_tastatura);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        s_culoare_led = findViewById(R.id.culoareLED_S);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.elemente_spinner)
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_culoare_led.setAdapter(adapter);

        cb_mecanica = findViewById(R.id.esteMecanica_CB);
        rg_culoare_switch = findViewById(R.id.rg_culoare_switch);
        schimbareStareRadio(rg_culoare_switch, false);

        cb_mecanica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                schimbareStareRadio(rg_culoare_switch, isChecked);

                if(!isChecked){
                    rg_culoare_switch.clearCheck();
                }
            }
        });

    }

    public void schimbareStareRadio(RadioGroup rg, boolean stare){
        rg.setEnabled(stare);
        for(int i = 0; i < rg.getChildCount(); i++){
            RadioButton r = (RadioButton) rg.getChildAt(i);
            r.setEnabled(stare);
            r.setClickable(stare);
        }
    }
    public void trimitereInformatii(View view) {
        EditText et_brand = findViewById(R.id.brandTastatura_ET);
        EditText et_nume = findViewById(R.id.numeTastatura_ET);
        CheckBox cb_TK = findViewById(R.id.areTK_CB);

        String brand = et_brand.getText().toString();
        String nume = et_nume.getText().toString();
        Boolean TK = cb_TK.isChecked();
        Boolean mecanica = cb_mecanica.isChecked();
        String culoare_led = s_culoare_led.getSelectedItem().toString();
        String culoare_switch;

        int id_switch_selectat = rg_culoare_switch.getCheckedRadioButtonId();
        if(id_switch_selectat == -1){
            culoare_switch = null;
        }
        else{
            RadioButton buton_selectat = findViewById(id_switch_selectat);
            culoare_switch = buton_selectat.getText().toString();
        }

        if(brand.isEmpty()){
            Toast.makeText(this, "Introdu un brand!", Toast.LENGTH_SHORT).show();
        }
        if(nume.isEmpty()){
            Toast.makeText(this, "Introdu un nume!", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent it = new Intent();
            it.putExtra("brand", brand);
            it.putExtra("nume", nume);
            it.putExtra("TK", TK);
            it.putExtra("mecanica", mecanica);
            it.putExtra("culoare_led", culoare_led);
            it.putExtra("culoare_switch", culoare_switch);
            setResult(RESULT_OK, it);
            finish();
        }
    }
}