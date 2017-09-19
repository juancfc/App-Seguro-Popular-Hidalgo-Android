package com.repss.apprepss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by maste on 14/10/2016.
 */
public class NotificacionesAdapter extends BaseAdapter {

    Context c;
    ArrayList<Notificacion> notificaciones;

    public NotificacionesAdapter(Context ctx, ArrayList<Notificacion> notificaciones) {
        this.c = ctx;
        this.notificaciones = notificaciones;
    }

    @Override
    public int getCount() {
        return notificaciones.size();
    }

    @Override
    public Object getItem(int position) {
        return notificaciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return notificaciones.indexOf(getItem(position));
    }

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
