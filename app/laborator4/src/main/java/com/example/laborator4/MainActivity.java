package com.example.laborator4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

    public void culegeDate(View view) {
        EditText et = findViewById(R.id.nume_ET);
        CheckBox cb = findViewById(R.id.eStudent_CB);

        String nume = et.getText().toString();
        Boolean eStudent = cb.isChecked();
    }

    public void recuperareInformatii(View view) {
        Intent it = new Intent(this, CompletareDateTastatura.class);
        startActivityForResult(it, 70);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 70){
            if(resultCode == RESULT_OK){
                String brand = data.getStringExtra("brand");
                String nume = data.getStringExtra("nume");
                Boolean TK = data.getBooleanExtra("TK", false);
                Boolean mecanica = data.getBooleanExtra("mecanica", false);
                String culoare_led = data.getStringExtra("culoare_led");

                StoianTudor tastatura1 = new StoianTudor(brand, nume, TK, mecanica, culoare_led);
                TextView afisare_informatii = findViewById(R.id.afisareInformatii_TV);
                afisare_informatii.setText(tastatura1.toString());
            }
        }
    }
}