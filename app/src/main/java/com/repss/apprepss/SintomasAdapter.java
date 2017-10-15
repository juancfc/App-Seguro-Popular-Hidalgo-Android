package com.repss.apprepss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Adaptador para el llenado de un control con items
 */
public class SintomasAdapter extends BaseAdapter{
    Context c;
    ArrayList<Sintomas> sintomas;
    ArrayList<Sintomas> filterList;

    /**
     * Se asignan los paramétros a variables locales
     * @param ctx
     * @param sintomas
     */
    public SintomasAdapter(Context ctx, ArrayList<Sintomas> sintomas) {
        this.c = ctx;
        this.sintomas = sintomas;
        this.filterList = sintomas;
    }

    /**
     * regresa el tamaño de la lista
     * @return tamaño de la lista
     */
    @Override
    public int getCount() {
        return sintomas.size();
    }

    /**
     * regresa el objeto
     * @param position posición del objeto
     * @return el objeto
     */
    @Override
    public Object getItem(int position) {
        return sintomas.get(position);
    }

    /**
     * regresa el id del objeto
     * @param position posición del objeto
     * @return el id del objeto
     */
    @Override
    public long getItemId(int position) {
        return sintomas.indexOf(getItem(position));
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
            convertView = inflater.inflate(R.layout.lstsintomas_item,null);
        }

        final TextView txvSintoma = (TextView) convertView.findViewById(R.id.txvSintoma);

        txvSintoma.setText(sintomas.get(position).nombre);

        return convertView;
    }

}
