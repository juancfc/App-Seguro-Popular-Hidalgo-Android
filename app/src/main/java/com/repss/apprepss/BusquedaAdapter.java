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
 * Adapter para llenar la el listview de busqueda de dependencias de salud
 */
public class BusquedaAdapter extends BaseAdapter implements Filterable {
    Context c;
    ArrayList<DependenciaSalud> dependenciaSalud;
    CustomFilter filter;
    ArrayList<DependenciaSalud> filterList;

    public BusquedaAdapter(Context ctx, ArrayList<DependenciaSalud> dependenciaSalud) {
        this.c = ctx;
        this.dependenciaSalud = dependenciaSalud;
        this.filterList = dependenciaSalud;
    }

    @Override
    public int getCount() {
        return dependenciaSalud.size();
    }

    @Override
    public Object getItem(int position) {
        return dependenciaSalud.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dependenciaSalud.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.lstbusqueda_item,null);
        }

        final TextView txvNombreDependencia = (TextView) convertView.findViewById(R.id.txvNombreDependencia);

        txvNombreDependencia.setText(dependenciaSalud.get(position).Nombre);

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

                ArrayList<DependenciaSalud> filters = new ArrayList<DependenciaSalud>();

                for (int i = 0;i < filterList.size();i++){
                    if(filterList.get(i).getNombre().toUpperCase().contains(constraint)){
                        DependenciaSalud m = new DependenciaSalud(filterList.get(i).getNombre());

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

            dependenciaSalud = (ArrayList<DependenciaSalud>) results.values;
            notifyDataSetChanged();

        }
    }
}
