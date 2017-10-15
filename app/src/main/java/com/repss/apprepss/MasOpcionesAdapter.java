package com.repss.apprepss;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Se gestiona el llenado de items
 */

public class MasOpcionesAdapter extends BaseAdapter {


    Context ctx;
    ArrayList<String> opciones;
    ArrayList<Integer> iconos;


    /**
     * Se asignan los parametros a variables locales
     * @param ctx
     * @param opciones
     * @param iconos
     */
    public MasOpcionesAdapter(Context ctx, ArrayList<String> opciones,ArrayList<Integer> iconos) {
        this.ctx = ctx;
        this.opciones = opciones;
        this.iconos = iconos;
    }

    /**
     * Regresa el tamaño de la lista
     * @return tamaño de la lista
     */
    @Override
    public int getCount() {
        return opciones.size();
    }

    /**
     * Regresa el item ubicado en la posición designada en el parámetro
     * @param i posición del item
     * @return el item
     */
    @Override
    public Object getItem(int i) {
        return opciones.get(i);
    }

    /**
     * Regresa el id del item
     * @param i posición del item
     * @return el id del item
     */
    @Override
    public long getItemId(int i) {
        return opciones.indexOf(getItem(i));
    }

    /**
     * Se asignan los valores a los controles del item
     * @param i
     * @param view
     * @param viewGroup
     * @return la vista del item
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null){
            view = inflater.inflate(R.layout.opcion_item,null);
        }

        ImageView iconOpcionImageView = (ImageView)view.findViewById(R.id.iconOpcionImageView);
        TextView nombreOpcionTextView = (TextView)view.findViewById(R.id.nombreOpcionTextView);

        nombreOpcionTextView.setText(opciones.get(i));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            iconOpcionImageView.setImageDrawable(ctx.getDrawable(iconos.get(i)));
        }
        else
        {
            iconOpcionImageView.setImageDrawable(ctx.getResources().getDrawable(iconos.get(i)));
        }


        return  view;
    }
}
