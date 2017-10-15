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
public class NotificacionesAdapter extends BaseAdapter {

    Context c;
    ArrayList<Notificacion> notificaciones;

    /**
     * Se asignan los paramétros a variables locales
     * @param ctx contexto de la actividad
     * @param notificaciones objeto Notificacion
     */
    public NotificacionesAdapter(Context ctx, ArrayList<Notificacion> notificaciones) {
        this.c = ctx;
        this.notificaciones = notificaciones;
    }

    /**
     * regresa el tamaño de la lista
     * @return tamaño de la lista
     */
    @Override
    public int getCount() {
        return notificaciones.size();
    }

    /**
     * regresa el objeto
     * @param position posición del objeto
     * @return el objeto
     */
    @Override
    public Object getItem(int position) {
        return notificaciones.get(position);
    }

    /**
     * regresa el id del objeto
     * @param position posición del objeto
     * @return el id del objeto
     */
    @Override
    public long getItemId(int position) {
        return notificaciones.indexOf(getItem(position));
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
            convertView = inflater.inflate(R.layout.notificacion_item,null);
        }

        final TextView txvTitulo = (TextView) convertView.findViewById(R.id.txvTitulo);
        final TextView txvDescripcion = (TextView) convertView.findViewById(R.id.txvDescripcion);

        txvTitulo.setText(notificaciones.get(position).titulo);
        txvDescripcion.setText(notificaciones.get(position).descripcion);

        return convertView;
    }

}
