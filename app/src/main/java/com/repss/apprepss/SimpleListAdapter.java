package com.repss.apprepss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.text.Collator;
import java.text.Normalizer;
import java.util.ArrayList;

/**
 * Created by maste on 06/03/2017.
 */

public class SimpleListAdapter extends BaseAdapter implements Filterable{

    Context c;
    ArrayList<String> items;
    CustomSimpleListFilter filter;
    ArrayList<String> filterList;

    public SimpleListAdapter(Context c, ArrayList<String> items) {
        this.c = c;
        this.items = items;
        this.filterList = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.indexOf(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.simple_item, null);
        }

        final TextView itemTextView = (TextView) convertView.findViewById(R.id.itemTextView);

        itemTextView.setText(items.get(position));

        return convertView;
    }


    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomSimpleListFilter();
        }

        return filter;
    }

    class CustomSimpleListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                constraint = constraint.toString().toUpperCase();

                ArrayList<String> filters = new ArrayList<>();

                for (int i = 0; i < filterList.size(); i++) {



                    if (stripAccents(filterList.get(i)).toUpperCase().contains(constraint)) {
                        String m = filterList.get(i);

                        filters.add(m);
                    }
                }
                results.count = filters.size();
                results.values = filters;
            } else {
                results.count = filterList.size();
                results.values = filterList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            items = (ArrayList<String>) results.values;
            notifyDataSetChanged();

        }

        public String stripAccents(String s)
        {
            s = Normalizer.normalize(s, Normalizer.Form.NFD);
            s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
            return s;
        }

    }
}
