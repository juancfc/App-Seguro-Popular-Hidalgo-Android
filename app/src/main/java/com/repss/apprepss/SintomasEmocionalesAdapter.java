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
 * Created by maste on 14/11/2016.
 */
public class SintomasEmocionalesAdapter extends BaseAdapter {

    Context c;
    ArrayList<SintomasEmocionales> sintomasEmocionales;
    ArrayList<SintomasEmocionales> filterList;

    public SintomasEmocionalesAdapter(Context ctx, ArrayList<SintomasEmocionales> sintomasEmocionales) {
        this.c = ctx;
        this.sintomasEmocionales = sintomasEmocionales;
        this.filterList = sintomasEmocionales;
    }

    @Override
    public int getCount() {
        return sintomasEmocionales.size();
    }

    @Override
    public Object getItem(int position) {
        return sintomasEmocionales.get(position);
    }

    @Override
    public long getItemId(int position) {
        return sintomasEmocionales.indexOf(getItem(position));
    }

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
