package com.example.repetaretest;

import android.content.Context;
import android.net.TetheringManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class AdaptorPisici extends BaseAdapter {
    private Context context;
    private List<Pisica> pisici;

    public AdaptorPisici(Context context, List<Pisica> pisici) {
        this.context = context;
        this.pisici = pisici;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Pisica> getPisici() {
        return pisici;
    }

    public void setPisici(List<Pisica> pisici) {
        this.pisici = pisici;
    }

    @Override
    public int getCount() {
        return pisici.size();
    }

    @Override
    public Object getItem(int position) {
        return pisici.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(context);
        View v = inflator.inflate(R.layout.layout_lista, parent, false);

        TextView tv_afisareRasa = v.findViewById(R.id.tv_afisareRasa);
        TextView tv_afisareCuloare = v.findViewById(R.id.tv_afisareCuloare);
        TextView tv_afisareGreutate = v.findViewById(R.id.tv_afisareGreutate);
        TextView tv_afisareArePedigree = v.findViewById(R.id.tv_afisareArePedigree);
        TextView tv_afisareEsteVaccinata = v.findViewById(R.id.tv_afisareEsteVaccinata);
        TextView tv_afisarePret = v.findViewById(R.id.tv_afisarePret);

        Pisica pisica = (Pisica) getItem(position);

        tv_afisareRasa.setText(pisica.getRasa());
        tv_afisareCuloare.setText(pisica.getCuloare());
        tv_afisareGreutate.setText(pisica.getGreutate().toString());
        String arePedigree = (pisica.getArePedigree()) ? "Are pedigree" : "Nu are pedigree";
        tv_afisareArePedigree.setText(arePedigree);
        String esteVaccinata = (pisica.getEsteVaccinata()) ? "Este vaccinata" : "Nu este vaccinata";
        tv_afisareEsteVaccinata.setText(esteVaccinata);
        tv_afisarePret.setText(pisica.getPret().toString());
        return v;
    }
}
