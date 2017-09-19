package com.repss.apprepss;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificacionesFragment extends Fragment {


    private MobileServiceClient mClient;
    MobileServiceTable<Notificaciones> mTableNotificaciones;
    ListView lstNotificaciones;
    DbHelper helper;
    Cursor cursor;
    NotificacionesAdapter mAdapter;
    ArrayList<Notificacion> listaNotificaciones = new ArrayList<Notificacion>();


    public NotificacionesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notificaciones, container, false);

        final SharedPreferences settings = getActivity().getSharedPreferences("UserSettings", Context.MODE_PRIVATE);

        try {
            mClient = new MobileServiceClient(
                    "https://seguropopularapp.azurewebsites.net",
                    getContext()
            );

            mTableNotificaciones = mClient.getTable(Notificaciones.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final ArrayList<Notificaciones> m = new ArrayList<Notificaciones>();
        lstNotificaciones = (ListView)view.findViewById(R.id.lstNotificaciones);

        mAdapter = new NotificacionesAdapter(getContext(),listaNotificaciones);
        lstNotificaciones.setAdapter(mAdapter);

        helper = new DbHelper(getContext());
        final SQLiteDatabase db = helper.getReadableDatabase();

        try {
            cursor = db.rawQuery(" SELECT * FROM notificaciones WHERE poliza=" + settings.getString("userFolio", "") + ";", null);
        }
        catch (Exception ex){
            Log.e("TAG",ex.toString());
        }
        if(listaNotificaciones.isEmpty()){
            while (cursor.moveToNext()) {
                Notificacion notificacion = new Notificacion();
                notificacion.id = Integer.valueOf(cursor.getString(0));
                notificacion.titulo = cursor.getString(1);
                notificacion.descripcion = cursor.getString(2);
                notificacion.poliza = cursor.getString(3);

                listaNotificaciones.add(notificacion);
            }
        }



        lstNotificaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Notificacion not = (Notificacion)lstNotificaciones.getItemAtPosition(position);

                Intent detalles = new Intent(getContext(),NotificacionDetalles.class);
                detalles.putExtra("Titulo",not.titulo);
                detalles.putExtra("Descripcion",not.descripcion);
                detalles.putExtra("NuevaNotificacion",false);
                startActivity(detalles);
            }
        });

        return view;
    }

}
