package com.example.laborator4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class AdaptorImagini extends BaseAdapter {
    private List<StoianTudor> tastaturiCuImagine;
    private Context context;

    public AdaptorImagini(List<StoianTudor> tastaturiCuImagine, Context context) {
        this.tastaturiCuImagine = tastaturiCuImagine;
        this.context = context;
    }

    public List<StoianTudor> getTastaturiCuImagine() {
        return tastaturiCuImagine;
    }

    public void setTastaturiCuImagine(List<StoianTudor> tastaturiCuImagine) {
        this.tastaturiCuImagine = tastaturiCuImagine;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return tastaturiCuImagine.size();
    }

    @Override
    public Object getItem(int position) {
        return tastaturiCuImagine.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(context);
        View v = inflator.inflate(R.layout.layout_lista_imagini, parent, false);

        ImageView iv_imagineTastatura = v.findViewById(R.id.iv_imagineTastatura);
        TextView tv_dateTastatura = v.findViewById(R.id.tv_dateTastatura);

        StoianTudor tastaturaCuImagine = (StoianTudor) getItem(position);

        iv_imagineTastatura.setImageBitmap(tastaturaCuImagine.getImagine());
        tv_dateTastatura.setText(tastaturaCuImagine.toString());
        return v;
    }
}
