package com.example.laborator4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CompletareDateTastatura extends AppCompatActivity {

    private Spinner s_culoare_led;
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
    }

    public void trimitereInformatii(View view) {
        EditText et_brand = findViewById(R.id.brandTastatura_ET);
        EditText et_nume = findViewById(R.id.numeTastatura_ET);
        CheckBox cb_TK = findViewById(R.id.areTK_CB);
        CheckBox cb_mecanica = findViewById(R.id.esteMecanica_CB);


        String brand = et_brand.getText().toString();
        String nume = et_nume.getText().toString();
        Boolean TK = cb_TK.isChecked();
        Boolean mecanica = cb_mecanica.isChecked();
        String culoare_led = s_culoare_led.getSelectedItem().toString();


        Intent it = new Intent();
        it.putExtra("brand", brand);
        it.putExtra("nume", nume);
        it.putExtra("TK", TK);
        it.putExtra("mecanica", mecanica);
        it.putExtra("culoare_led", culoare_led);
        setResult(RESULT_OK, it);
        finish();
    }
}