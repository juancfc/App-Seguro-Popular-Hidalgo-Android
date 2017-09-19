package com.repss.apprepss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by maste on 15/07/2016.
 */
public class AfiliacionAdapter extends BaseAdapter implements Filterable {

    Context c;
    ArrayList<CentroAfiliación> centroAfiliacion;
    CustomFilter filter;
    ArrayList<CentroAfiliación> filterList;

    public AfiliacionAdapter(Context ctx, ArrayList<CentroAfiliación> centroAfiliacion) {
        this.c = ctx;
        this.centroAfiliacion = centroAfiliacion;
        this.filterList = centroAfiliacion;
    }

    @Override
    public int getCount() {
        return centroAfiliacion.size();
    }

    @Override
    public Object getItem(int position) {
        return centroAfiliacion.get(position);
    }

    @Override
    public long getItemId(int position) {
        return centroAfiliacion.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.lstbusqueda_item,null);
        }

        final TextView txvNombreDependencia = (TextView) convertView.findViewById(R.id.txvNombreDependencia);

        txvNombreDependencia.setText(centroAfiliacion.get(position).Nombre);

        return convertView;
    }

    @Override
    public Filter getFilter() {

        if (filter == null){
            filter = new CustomFilter();
        }

        return filter;
    }

    class CustomFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if(constraint != null && constraint.length() > 0){
                constraint = constraint.toString().toUpperCase();

                ArrayList<CentroAfiliación> filters = new ArrayList<CentroAfiliación>();

                for (int i = 0;i < filterList.size();i++){
                    if(filterList.get(i).getNombre().toUpperCase().contains(constraint)){
                        CentroAfiliación m = new CentroAfiliación(filterList.get(i).getNombre());

                        filters.add(m);
                    }
                }
                results.count = filters.size();
                results.values = filters;
            }else {
                results.count = filterList.size();
                results.values = filterList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            centroAfiliacion = (ArrayList<CentroAfiliación>) results.values;
            notifyDataSetChanged();

        }
    }

}
