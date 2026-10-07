package com.example.laborator2;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
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

    private static final String TAG = "laborator2";

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "Eroare la pornire!");
        Log.w(TAG, "Avertisment la pornire!");
        Log.d(TAG, "Debug la pornire!");
        Log.i(TAG, "Info la pornire!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "Eroare la reluare!");
        Log.w(TAG, "Avertisment la reluare!");
        Log.d(TAG, "Debug la reluare!");
        Log.i(TAG, "Info la reluare!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "Eroare la pauza!");
        Log.w(TAG, "Avertisment la pauza!");
        Log.d(TAG, "Debug la pauza!");
        Log.i(TAG, "Info la pauza!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "Eroare la oprire!");
        Log.w(TAG, "Avertisment la oprire!");
        Log.d(TAG, "Debug la oprire!");
        Log.i(TAG, "Info la oprire!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "Eroare la distrugere!");
        Log.w(TAG, "Avertisment la distrugere!");
        Log.d(TAG, "Debug la distrugere!");
        Log.i(TAG, "Info la distrugere!");
    }
}