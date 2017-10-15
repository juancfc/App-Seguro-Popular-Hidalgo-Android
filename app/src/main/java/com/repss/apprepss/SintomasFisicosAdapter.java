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
 * Adaptador para el llenado de un control con items
 */
public class SintomasFisicosAdapter extends BaseAdapter {

    Context c;
    ArrayList<SintomasFisicos> sintomasFisicos;
    ArrayList<SintomasFisicos> filterList;

    /**
     * Se asignan los paramétros a variables locales
     * @param ctx
     * @param sintomasFisicos
     */
    public SintomasFisicosAdapter(Context ctx, ArrayList<SintomasFisicos> sintomasFisicos) {
        this.c = ctx;
        this.sintomasFisicos = sintomasFisicos;
        this.filterList = sintomasFisicos;
    }

    /**
     * regresa el tamaño de la lista
     * @return tamaño de la lista
     */
    @Override
    public int getCount() {
        return sintomasFisicos.size();
    }

    /**
     * regresa el objeto
     * @param position posición del objeto
     * @return el objeto
     */
    @Override
    public Object getItem(int position) {
        return sintomasFisicos.get(position);
    }

    /**
     * regresa el id del objeto
     * @param position posición del objeto
     * @return el id del objeto
     */
    @Override
    public long getItemId(int position) {
        return sintomasFisicos.indexOf(getItem(position));
    }

    /**
     * Se asignan los valores a los controles de la vista
     * @param position
     * @param convertView
     * @param parent
     * @return la vista del item
     */
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
