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
public class SintomasEmocionalesAdapter extends BaseAdapter {

    Context c;
    ArrayList<SintomasEmocionales> sintomasEmocionales;
    ArrayList<SintomasEmocionales> filterList;

    /**
     * Se asignan los paramétros a variables locales
     * @param ctx
     * @param sintomasEmocionales
     */
    public SintomasEmocionalesAdapter(Context ctx, ArrayList<SintomasEmocionales> sintomasEmocionales) {
        this.c = ctx;
        this.sintomasEmocionales = sintomasEmocionales;
        this.filterList = sintomasEmocionales;
    }

    /**
     * regresa el tamaño de la lista
     * @return tamaño de la lista
     */
    @Override
    public int getCount() {
        return sintomasEmocionales.size();
    }

    /**
     * regresa el objeto
     * @param position posición del objeto
     * @return el objeto
     */
    @Override
    public Object getItem(int position) {
        return sintomasEmocionales.get(position);
    }

    /**
     * regresa el id del objeto
     * @param position posición del objeto
     * @return el id del objeto
     */
    @Override
    public long getItemId(int position) {
        return sintomasEmocionales.indexOf(getItem(position));
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
            convertView = inflater.inflate(R.layout.lstsintomasemocionales_item,null);
        }


        final TextView txvIntensidadEmocional = (TextView) convertView.findViewById(R.id.txvIntensidadEmocional);
        final TextView txvFechaEmocional = (TextView) convertView.findViewById(R.id.txvFechaEmocional);
        final ImageView imvEmocional = (ImageView) convertView.findViewById(R.id.imvEmocional);

        txvIntensidadEmocional.setText(sintomasEmocionales.get(position).intensidad);
        txvFechaEmocional.setText(sintomasEmocionales.get(position).fecha);

        switch (sintomasEmocionales.get(position).intensidad){
            case "Asustado":
                imvEmocional.setImageResource(R.drawable.asustado);
                break;
            case "Cansado":
                imvEmocional.setImageResource(R.drawable.cansado);
                break;
            case "Contento":
                imvEmocional.setImageResource(R.drawable.contento);
                break;
            case "Enojado":
                imvEmocional.setImageResource(R.drawable.enojado);
                break;
            case "Hambriento":
                imvEmocional.setImageResource(R.drawable.hambriento);
                break;
            case "Triste":
                imvEmocional.setImageResource(R.drawable.triste);
                break;
        }

        return convertView;
    }

}
