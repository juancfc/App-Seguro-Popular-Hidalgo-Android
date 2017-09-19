package com.repss.apprepss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by maste on 10/11/2016.
 */
public class SintomasFisicosAdapter extends BaseAdapter {

    Context c;
    ArrayList<SintomasFisicos> sintomasFisicos;
    ArrayList<SintomasFisicos> filterList;

    public SintomasFisicosAdapter(Context ctx, ArrayList<SintomasFisicos> sintomasFisicos) {
        this.c = ctx;
        this.sintomasFisicos = sintomasFisicos;
        this.filterList = sintomasFisicos;
    }

    @Override
    public int getCount() {
        return sintomasFisicos.size();
    }

    @Override
    public Object getItem(int position) {
        return sintomasFisicos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return sintomasFisicos.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.lstsintomasfisicos_item,null);
        }

        final TextView txvDescripcionFisico = (TextView) convertView.findViewById(R.id.txvDescripcionFisico);
        final TextView txvIntensidadFisico = (TextView) convertView.findViewById(R.id.txvIntensidadFisico);
        final TextView txvFechaFisico = (TextView) convertView.findViewById(R.id.txvFechaFisico);
        final ImageView imvDolor = (ImageView) convertView.findViewById(R.id.imvDolor);

        txvDescripcionFisico.setText(sintomasFisicos.get(position).descripcion);
        txvIntensidadFisico.setText(sintomasFisicos.get(position).intensidad);
        txvFechaFisico.setText(sintomasFisicos.get(position).fecha);

        switch (sintomasFisicos.get(position).intensidad){
            case "Sin Dolor":
                imvDolor.setImageResource(R.drawable.sindolor);
                break;
            case "Dolor Leve":
                imvDolor.setImageResource(R.drawable.leve);
                break;
            case "Dolor Moderado":
                imvDolor.setImageResource(R.drawable.moderado);
                break;
            case "Dolor Severo":
                imvDolor.setImageResource(R.drawable.severo);
                break;
            case "Dolor Muy Severo":
                imvDolor.setImageResource(R.drawable.muysevero);
                break;
            case "Máximo Dolor":
                imvDolor.setImageResource(R.drawable.maximodolor);
                break;
        }

        return convertView;
    }
}
