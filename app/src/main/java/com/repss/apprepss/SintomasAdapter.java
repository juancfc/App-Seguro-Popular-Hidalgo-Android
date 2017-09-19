package com.repss.apprepss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by maste on 02/09/2016.
 */
public class SintomasAdapter extends BaseAdapter{
    Context c;
    ArrayList<Sintomas> sintomas;
    ArrayList<Sintomas> filterList;

    public SintomasAdapter(Context ctx, ArrayList<Sintomas> sintomas) {
        this.c = ctx;
        this.sintomas = sintomas;
        this.filterList = sintomas;
    }

    @Override
    public int getCount() {
        return sintomas.size();
    }

    @Override
    public Object getItem(int position) {
        return sintomas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return sintomas.indexOf(getItem(position));
    }

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
