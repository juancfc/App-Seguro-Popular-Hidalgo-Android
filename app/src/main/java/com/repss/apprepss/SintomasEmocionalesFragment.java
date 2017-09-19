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


public class SintomasEmocionalesFragment extends Fragment {

    ListView lstSintomasEmocionales;

    DbHelper helper;
    DataBaseManager manager;
    Cursor cursor;
    SintomasEmocionalesAdapter mAdapter;

    ArrayList<SintomasEmocionales> listaEmocional = new ArrayList<SintomasEmocionales>();


    public SintomasEmocionalesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sintomas_emocionales, container, false);

        final Bundle bundle = getActivity().getIntent().getExtras();

        helper = new DbHelper(getActivity());

        FloatingActionButton fabAgregarSintomaEmocional = (FloatingActionButton)v.findViewById(R.id.fabAgregarSintomaEmocional);

        lstSintomasEmocionales = (ListView)v.findViewById(R.id.lstSintomasEmocionales);

        helper = new DbHelper(getActivity());
        final SQLiteDatabase db = helper.getReadableDatabase();

        //Instruccion para realizar la consulta y almacenarlo en un cursor
        try{
            cursor = db.rawQuery(" SELECT * FROM sintoma_emocional WHERE _id_sintoma=" + bundle.getInt("idSintoma") + ";", null);
        }catch (Exception ex){
            Log.e("TAG",ex.toString());
        }

        if(listaEmocional.isEmpty()) {
            while (cursor.moveToNext()) {
                SintomasEmocionales sintomasEmocionales = new SintomasEmocionales();
                sintomasEmocionales.id = Integer.valueOf(cursor.getString(0));
                sintomasEmocionales.intensidad = cursor.getString(1);
                sintomasEmocionales.fecha = cursor.getString(2);
                sintomasEmocionales.idSintoma = Integer.valueOf(cursor.getString(3));

                listaEmocional.add(sintomasEmocionales);
            }
        }

        mAdapter = new SintomasEmocionalesAdapter(getActivity(),listaEmocional);
        lstSintomasEmocionales.setAdapter(mAdapter);

        fabAgregarSintomaEmocional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AgregarSintomaEmocional.class);
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