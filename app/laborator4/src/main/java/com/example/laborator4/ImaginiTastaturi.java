package com.example.laborator4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImaginiTastaturi extends AppCompatActivity {

    private ListView lv_listaImagini;
    private List<StoianTudor> tastaturiCuImagine = new ArrayList<>();
    private List<String> linkuriImagini = new ArrayList<>();
    private List<Bitmap> bitmapsImagini = new ArrayList<>();
    private AdaptorImagini adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imagini_tastaturi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lv_listaImagini = findViewById(R.id.lv_listaImagini);

        linkuriImagini.add("https://lcdn.altex.ro/media/catalog/product/T/a/Tastatura_Gaming_RAZER_Ornata_V3_X_RGB_USB_Layout_US_negru_3_.jpg");
        linkuriImagini.add("https://lcdn.altex.ro/media/catalog/product/t/a/tastatura_steelseries_apex_9_mini_02_fe707d17.jpg");
        linkuriImagini.add("https://lcdn.altex.ro/media/catalog/product/r/o/rog_strix_scope_ii_x_01_0f18c476.jpg");
        linkuriImagini.add("https://lcdn.altex.ro/media/catalog/product/t/a/tastatura_logitech_g_pro_x_tkl_black_01_53393c9d.jpg");
        linkuriImagini.add("https://lcdn.altex.ro/media/catalog/product/t/a/tasmxk83za_1_93a27189.jpg");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for(String link:linkuriImagini){
                    HttpURLConnection conn = null;
                    try{
                        URL url = new URL(link);
                        conn = (HttpURLConnection) url.openConnection();
                        InputStream is = conn.getInputStream();
                        bitmapsImagini.add(BitmapFactory.decodeStream(is));
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    finally {
                        if(conn != null){
                            conn.disconnect();
                        }
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tastaturiCuImagine.add(new StoianTudor("Razer", "R1", true, true, "Verde", "Verde", bitmapsImagini.get(0), "https://altex.ro/tastatura-gaming-razer-ornata-v3-x-rgb-usb-layout-us-negru/cpd/TASRZ0344701R3M/?_gl=1*1v36221*_up*MQ..*_ga*MTA0NzE2NDk2MS4xNzYyMjQwOTA3*_ga_K29BS2DN02*czE3OTUwMTAwNjAkbzYkZzAkdDE3OTUwMTAwNjAkajYwJGwwJGg3MjQyOTgwNCRkLXZ0UzJ4NWVabTRuOG0wTk53N2RTam5uYWdXVjN4ck1GUQ.."));
                        tastaturiCuImagine.add(new StoianTudor("SteelSeries", "SS1", false, true, "Multicolor", "Rosu", bitmapsImagini.get(1), "https://altex.ro/tastatura-gaming-steelseries-apex-9-mini-linear-optipoint-switch-usb-layout-us-negru/cpd/TASSSAPEX9MINI/?_gl=1*1v36221*_up*MQ..*_ga*MTA0NzE2NDk2MS4xNzYyMjQwOTA3*_ga_K29BS2DN02*czE3OTUwMTAwNjAkbzYkZzAkdDE3OTUwMTAwNjAkajYwJGwwJGg3MjQyOTgwNCRkLXZ0UzJ4NWVabTRuOG0wTk53N2RTam5uYWdXVjN4ck1GUQ.."));
                        tastaturiCuImagine.add(new StoianTudor("ROG", "ROG1", true, true, "Multicolor", "Negru", bitmapsImagini.get(2), "https://altex.ro/tastatura-gaming-mecanica-asus-rog-strix-scope-ii-x-usb-layout-us-negru/cpd/90MP03XFBKUA20/?_gl=1*1v36221*_up*MQ..*_ga*MTA0NzE2NDk2MS4xNzYyMjQwOTA3*_ga_K29BS2DN02*czE3OTUwMTAwNjAkbzYkZzAkdDE3OTUwMTAwNjAkajYwJGwwJGg3MjQyOTgwNCRkLXZ0UzJ4NWVabTRuOG0wTk53N2RTam5uYWdXVjN4ck1GUQ.."));
                        tastaturiCuImagine.add(new StoianTudor("Logitech", "L1", false, true, "Albastru", "Albastru", bitmapsImagini.get(3), "https://altex.ro/tastatura-gaming-mecanica-cu-fir-logitech-pro-x-tkl-rapid-switch-analog-usb-layout-us-negru/cpd/TAS920013233/?_gl=1*1v36221*_up*MQ..*_ga*MTA0NzE2NDk2MS4xNzYyMjQwOTA3*_ga_K29BS2DN02*czE3OTUwMTAwNjAkbzYkZzAkdDE3OTUwMTAwNjAkajYwJGwwJGg3MjQyOTgwNCRkLXZ0UzJ4NWVabTRuOG0wTk53N2RTam5uYWdXVjN4ck1GUQ.."));
                        tastaturiCuImagine.add(new StoianTudor("Apple", "A1", true, false, "Multicolor", "Nu are", bitmapsImagini.get(4), "https://altex.ro/tastatura-wireless-apple-magic-keyboard-cu-touch-id-2024-usb-bluetooth-layout-int-english-negru/cpd/TASMXK83ZA/?_gl=1*1v36221*_up*MQ..*_ga*MTA0NzE2NDk2MS4xNzYyMjQwOTA3*_ga_K29BS2DN02*czE3OTUwMTAwNjAkbzYkZzAkdDE3OTUwMTAwNjAkajYwJGwwJGg3MjQyOTgwNCRkLXZ0UzJ4NWVabTRuOG0wTk53N2RTam5uYWdXVjN4ck1GUQ.."));

                        adaptor = new AdaptorImagini(tastaturiCuImagine, getApplicationContext());

                        lv_listaImagini.setAdapter(adaptor);
                    }
                });
            }
        });

        lv_listaImagini.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
                it.putExtra("link", tastaturiCuImagine.get(position).getLink());
                startActivity(it);
            }
        });
    }
}