package com.repss.apprepss;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SintomasFisicosFragment extends Fragment {

    ListView lstSintomasFisicos;

    DbHelper helper;
    DataBaseManager manager;
    Cursor cursor;
    SintomasFisicosAdapter mAdapter;

    ArrayList<SintomasFisicos> listaFisico = new ArrayList<SintomasFisicos>();

    public SintomasFisicosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sintomas_fisicos, container, false);

        final Bundle bundle = getActivity().getIntent().getExtras();

        helper = new DbHelper(getActivity());

        FloatingActionButton fabAgregarSintomaFisico = (FloatingActionButton)v.findViewById(R.id.fabAgregarSintomaFisico);

        lstSintomasFisicos = (ListView)v.findViewById(R.id.lstSintomasFisicos   );

        helper = new DbHelper(getActivity());
        final SQLiteDatabase db = helper.getReadableDatabase();

        //Instruccion para realizar la consulta y almacenarlo en un cursor
        try{
            cursor = db.rawQuery(" SELECT * FROM sintoma_fisico WHERE _id_sintoma=" + bundle.getInt("idSintoma") + ";", null);
        }catch (Exception ex){
            Log.e("TAG",ex.toString());
        }

        if(listaFisico.isEmpty()) {
            while (cursor.moveToNext()) {
                SintomasFisicos sintomasFisicos = new SintomasFisicos();
                sintomasFisicos.id = Integer.valueOf(cursor.getString(0));
                sintomasFisicos.descripcion = cursor.getString(1);
                sintomasFisicos.intensidad = cursor.getString(2);
                sintomasFisicos.fecha = cursor.getString(3);
                sintomasFisicos.idSintoma = Integer.valueOf(cursor.getString(4));

                listaFisico.add(sintomasFisicos);
            }
        }

        mAdapter = new SintomasFisicosAdapter(getActivity(),listaFisico);
        lstSintomasFisicos.setAdapter(mAdapter);

        fabAgregarSintomaFisico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AgregarSintomaFisico.class);
                Bundle b = new Bundle();
                try {
                    b.putInt("idSintoma", bundle.getInt("idSintoma"));
                }catch (Exception ex){
                    Log.e("TAG",ex.toString());
                }
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        return v;
    }

}
