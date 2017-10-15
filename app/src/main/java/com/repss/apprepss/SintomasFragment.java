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
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Se muestra la lista con los sintomas almacenados en la bd
 */
public class SintomasFragment extends Fragment {

    FloatingActionButton fabAgregarSintoma;
    ListView lstSintomas;

    DbHelper helper;
    DataBaseManager manager;
    Cursor cursor;
    SintomasAdapter mAdapter;

    ArrayList<Sintomas> lista = new ArrayList<Sintomas>();

    public SintomasFragment() {
        // Required empty public constructor
    }

    /**
     * Se carga la lista de sintomas en el control, tambien hay un botón para agregar un nuevo síntoma
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sintomas, container, false);
        helper = new DbHelper(getActivity());

        fabAgregarSintoma = (FloatingActionButton)v.findViewById(R.id.fabAgregarSintoma);

        lstSintomas = (ListView)v.findViewById(R.id.lstSintomas);

        helper = new DbHelper(getActivity());
        final SQLiteDatabase db = helper.getReadableDatabase();

        //Instruccion para realizar la consulta y almacenarlo en un cursor
        try{
        cursor = db.rawQuery(" SELECT * FROM sintomas;", null);
        }catch (Exception ex){
            Log.e("TAG",ex.toString());
        }

        if(lista.isEmpty()) {
            while (cursor.moveToNext()) {
                Sintomas sintomas = new Sintomas();
                sintomas.id = Integer.valueOf(cursor.getString(0));
                sintomas.nombre = cursor.getString(1);

                lista.add(sintomas);
            }
        }

        mAdapter = new SintomasAdapter(getActivity(),lista);
        lstSintomas.setAdapter(mAdapter);


        fabAgregarSintoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),AgregarSintoma.class));
            }
        });

        lstSintomas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sintomas sintoma = (Sintomas)lstSintomas.getItemAtPosition(position);
                Intent navigationSintoma = new Intent(getActivity(),ListaSintomas.class);
                Bundle b = new Bundle();
                b.putInt("idSintoma", sintoma.id);
                navigationSintoma.putExtras(b);
                startActivity(navigationSintoma);
            }
        });

        return v;
    }

}
