package com.example.laborator4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdaptorTastaturi extends BaseAdapter {

    private Context context;
    private List<StoianTudor> tastaturi;

    public AdaptorTastaturi(Context ctx, List<StoianTudor> tst){
        this.context = ctx;
        this.tastaturi = tst;
    }

    @Override
    public int getCount() {
        return this.tastaturi.size();
    }

    @Override
    public Object getItem(int position) {
        return this.tastaturi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View v = inflater.inflate(R.layout.layout_lista_tastaturi, parent,false);
        TextView tv1 = v.findViewById(R.id.tv_lista_brand_nume);
        TextView tv2 = v.findViewById(R.id.tv_lista_numpad);
        TextView tv3 = v.findViewById(R.id.tv_lista_mecanica_switch);
        TextView tv4 = v.findViewById(R.id.tv_lista_led);

        StoianTudor tastatura = (StoianTudor) getItem(position);

        StringBuilder sb1 = new StringBuilder(tastatura.getBrand());
        sb1.append(", ");
        sb1.append(tastatura.getNume());
        tv1.setText(sb1);

        StringBuilder sb2 = new StringBuilder();
        if(tastatura.getTenkey() == false){
            sb2.append("Fara numpad");
            tv2.setText(sb2);
        }
        else{
            sb2.append("Cu numpad");
            tv2.setText(sb2);
        }

        StringBuilder sb3 = new StringBuilder();
        if(tastatura.getMecanica() == true){
            sb3.append("Mecanica, switchuri: ");
            sb3.append(tastatura.getCuloareSwitch());
            tv3.setText(sb3);
        }
        else{
            sb3.append("Nu este mecanica");
            tv3.setText(sb3);
        }

        StringBuilder sb4 = new StringBuilder("Culoare led: ");
        sb4.append(tastatura.getCuloareLED());
        tv4.setText(sb4);

        return v;
    }
}
