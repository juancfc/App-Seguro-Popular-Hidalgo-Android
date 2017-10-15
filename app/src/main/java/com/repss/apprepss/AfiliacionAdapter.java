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
 * Adapter para llenar el listview de busqueda de centros de afiliacion
 */
public class AfiliacionAdapter extends BaseAdapter implements Filterable {

    Context c;
    ArrayList<CentroAfiliacion> centroAfiliacion;
    CustomFilter filter;
    ArrayList<CentroAfiliacion> filterList;

    /**
     *Se asignan los parametros a variables locales para su uso
     * @param ctx Contexto de la activity
     * @param centroAfiliacion Lista con los centros de afiliación
     */
    public AfiliacionAdapter(Context ctx, ArrayList<CentroAfiliacion> centroAfiliacion) {
        this.c = ctx;
        this.centroAfiliacion = centroAfiliacion;
        this.filterList = centroAfiliacion;
    }

    /**
     * Regresa el tamaño de la lista de centros de afiliación
     * @return tamaño de la lista de centros de afiliación
     */
    @Override
    public int getCount() {
        return centroAfiliacion.size();
    }

    /**
     * Obtener objeto de la lista con el id especificado
     * @param position posición del objeto que se quiere obtener
     * @return regresa el objeto de busqueda
     */
    @Override
    public Object getItem(int position) {
        return centroAfiliacion.get(position);
    }

    /**
     * Obtener el id del objeto con el id especificado
     * @param position posición del objeto
     * @return regresa el id
     */
    @Override
    public long getItemId(int position) {
        return centroAfiliacion.indexOf(getItem(position));
    }

    /**
     * Se asignan los valores los controles de la vista
     * @param position posición del objeto a asignar valores
     * @param convertView
     * @param parent
     * @return
     */
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


    /**
     * Obtener el filtro de la lista
     * @return regresa el filtrado de datos
     */
    @Override
    public Filter getFilter() {

        if (filter == null){
            filter = new CustomFilter();
        }

        return filter;
    }

    /**
     * Clase para realizar filtrado de items de la lista
     */
    class CustomFilter extends Filter
    {
        /**
         * Filtro de los items dependiendo el texto ingresado
         * @param constraint texto ingresado
         * @return regresa los resultados
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if(constraint != null && constraint.length() > 0){
                constraint = constraint.toString().toUpperCase();

                ArrayList<CentroAfiliacion> filters = new ArrayList<CentroAfiliacion>();

                for (int i = 0;i < filterList.size();i++){
                    if(filterList.get(i).getNombre().toUpperCase().contains(constraint)){
                        CentroAfiliacion m = new CentroAfiliacion(filterList.get(i).getNombre());

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

        /**
         * Se publican los resultados
         * @param constraint texto ingresado
         * @param results resultado de la busqueda
         */
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            centroAfiliacion = (ArrayList<CentroAfiliacion>) results.values;
            notifyDataSetChanged();

        }
    }

}
