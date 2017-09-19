package com.repss.apprepss;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.repss.apprepss.BusquedaAdapter;
import com.repss.apprepss.DbHelper;
import com.repss.apprepss.DependenciaSalud;
import com.repss.apprepss.Favoritos;
import com.repss.apprepss.R;

import java.util.ArrayList;


public class FavoritosFragment extends Fragment {

    DbHelper helper;
    Cursor cursor, cursor1;
    ArrayList<Favoritos> listaFavoritos = new ArrayList<Favoritos>();
    ArrayList<DependenciaSalud> listaDependenciaSalud = new ArrayList<DependenciaSalud>();
    BusquedaAdapter mAdapterBusqueda;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);
        final ListView lstFavoritos = (ListView) view.findViewById(R.id.lstFavoritos);

        helper = new DbHelper(getContext());


        SQLiteDatabase db = helper.getReadableDatabase();

        //Instruccion para realizar la consulta y almacenarlo en un cursor
        cursor = db.rawQuery(" SELECT * FROM favoritos;", null);

        //cursor = db.rawQuery(" SELECT * FROM centros_afiliacion;", null);

        while (cursor.moveToNext()) {
            cursor1 = db.rawQuery(" SELECT * FROM dependencias_salud WHERE _id = " + cursor.getString(1) + ";", null);

            while (cursor1.moveToNext()) {
                DependenciaSalud dependenciaSalud = new DependenciaSalud();
                dependenciaSalud.id = Integer.valueOf(cursor1.getString(0));
                dependenciaSalud.Nombre = cursor1.getString(1);
                dependenciaSalud.Clues = cursor1.getString(2);
                dependenciaSalud.Clave = cursor1.getString(3);
                dependenciaSalud.Municipio = cursor1.getString(4);
                dependenciaSalud.Localidad = cursor1.getString(5);
                dependenciaSalud.TipoEstablecimiento = cursor1.getString(6);
                dependenciaSalud.Direccion = cursor1.getString(7);
                dependenciaSalud.CodigoPostal = cursor1.getString(8);
                dependenciaSalud.Telefono = cursor1.getString(9);
                dependenciaSalud.Horario = cursor1.getString(10);
                dependenciaSalud.AccionesSaludPublica = cursor1.getString(11);
                dependenciaSalud.ConsultaMedicinaGeneralFamiliar = cursor1.getString(12);
                dependenciaSalud.Odontologia = cursor1.getString(13);
                dependenciaSalud.Anestesiologia = cursor1.getString(14);
                dependenciaSalud.Cirugia = cursor1.getString(15);
                dependenciaSalud.GinecologiaObstetricia = cursor1.getString(16);
                dependenciaSalud.MedicinaInterna = cursor1.getString(17);
                dependenciaSalud.Pediatra = cursor1.getString(18);
                dependenciaSalud.TraumaOrtopedia = cursor1.getString(19);
                dependenciaSalud.AtencionUrgencias = cursor1.getString(20);
                dependenciaSalud.Radiologia = cursor1.getString(21);
                dependenciaSalud.LaboratorioClinico = cursor1.getString(22);
                dependenciaSalud.BancoSangre = cursor1.getString(23);
                dependenciaSalud.Latitud = cursor1.getString(24);
                dependenciaSalud.Longitud = cursor1.getString(27);
                dependenciaSalud.Gestor = cursor1.getString(25);
                dependenciaSalud.Unidad = cursor1.getString(26);


                listaDependenciaSalud.add(dependenciaSalud);
            }

            mAdapterBusqueda = new BusquedaAdapter(getContext(), listaDependenciaSalud);
            lstFavoritos.setAdapter(mAdapterBusqueda);
        }

        lstFavoritos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DependenciaSalud ds = (DependenciaSalud) lstFavoritos.getItemAtPosition(position);

                for(DependenciaSalud dependencia : listaDependenciaSalud){
                    if(ds.Nombre.equals(dependencia.Nombre)){
                        Intent detalles = new Intent(getContext(),DependenciaSaludDetalles.class);
                        detalles.putExtra("id",dependencia.id);
                        detalles.putExtra("nombre",dependencia.Nombre);
                        detalles.putExtra("clues",dependencia.Clues);
                        detalles.putExtra("clave",dependencia.Clave);
                        detalles.putExtra("municipio",dependencia.Municipio);
                        detalles.putExtra("localidad",dependencia.Localidad);
                        detalles.putExtra("tipoEstablecimiento",dependencia.TipoEstablecimiento);
                        detalles.putExtra("direccion",dependencia.Direccion);
                        detalles.putExtra("codigoPostal",dependencia.CodigoPostal);
                        detalles.putExtra("telefono",dependencia.Telefono);
                        detalles.putExtra("horario",dependencia.Horario);
                        detalles.putExtra("latitud", dependencia.Latitud);
                        detalles.putExtra("longitud", dependencia.Longitud);
                        detalles.putExtra("servicio1", dependencia.AccionesSaludPublica);
                        detalles.putExtra("servicio2", dependencia.ConsultaMedicinaGeneralFamiliar);
                        detalles.putExtra("servicio3", dependencia.Odontologia);
                        detalles.putExtra("servicio4", dependencia.Anestesiologia);
                        detalles.putExtra("servicio5", dependencia.Cirugia);
                        detalles.putExtra("servicio6", dependencia.GinecologiaObstetricia);
                        detalles.putExtra("servicio7", dependencia.MedicinaInterna);
                        detalles.putExtra("servicio8", dependencia.Pediatra);
                        detalles.putExtra("servicio9", dependencia.TraumaOrtopedia);
                        detalles.putExtra("servicio10", dependencia.AtencionUrgencias);
                        detalles.putExtra("servicio11", dependencia.Radiologia);
                        detalles.putExtra("servicio12", dependencia.LaboratorioClinico);
                        detalles.putExtra("servicio13", dependencia.BancoSangre);
                        detalles.putExtra("gestor", dependencia.Gestor);
                        detalles.putExtra("unidad", dependencia.Unidad);
                        startActivity(detalles);
                    }
                }


            }
        });

        return view;

    }
}
