package com.repss.apprepss;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Busqueda extends AppCompatActivity {

    DbHelper helper;
    Cursor cursor;
    CentroAfiliación centroAfiliación;
    DependenciaSalud dependenciaSalud;
    ArrayList<CentroAfiliación> lista = new ArrayList<CentroAfiliación>();
    ArrayList<DependenciaSalud> listaDS = new ArrayList<>();
    BusquedaAdapter mAdapterBusqueda;
    AfiliacionAdapter mAdapterAfiliacion;
    android.widget.SearchView etxFindWarehouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etxFindWarehouse = (android.widget.SearchView)findViewById(R.id.svFindWarehouse);
        final ListView lstBusqueda = (ListView)findViewById(R.id.lstBusqueda);
        final ListView lstBusquedaAfiliacion = (ListView)findViewById(R.id.lstBusquedaAfiliacion);

        helper = new DbHelper(this);


        final SQLiteDatabase db = helper.getReadableDatabase();

        //Instruccion para realizar la consulta y almacenarlo en un cursor
        cursor = db.rawQuery(" SELECT * FROM centros_afiliacion;", null);

        while(cursor.moveToNext()){
            centroAfiliación = new CentroAfiliación();
            centroAfiliación.id = Integer.valueOf(cursor.getString(0));
            centroAfiliación.Nombre = cursor.getString(1);
            centroAfiliación.Direccion = cursor.getString(2);
            centroAfiliación.Telefono = cursor.getString(3);
            centroAfiliación.Responsable = cursor.getString(4);
            centroAfiliación.TelefonoResponsable = cursor.getString(5);
            centroAfiliación.Horario = cursor.getString(6);
            centroAfiliación.Correo = cursor.getString(7);
            centroAfiliación.Latitud = cursor.getString(8);
            centroAfiliación.Longitud = cursor.getString(9);

            lista.add(centroAfiliación);
        }

        cursor = db.rawQuery(" SELECT * FROM dependencias_salud;", null);

        while(cursor.moveToNext()){
            dependenciaSalud = new DependenciaSalud();
            dependenciaSalud.id = Integer.valueOf(cursor.getString(0));
            dependenciaSalud.Nombre = cursor.getString(1);
            dependenciaSalud.Clues = cursor.getString(2);
            dependenciaSalud.Clave = cursor.getString(3);
            dependenciaSalud.Municipio = cursor.getString(4);
            dependenciaSalud.Localidad = cursor.getString(5);
            dependenciaSalud.TipoEstablecimiento = cursor.getString(6);
            dependenciaSalud.Direccion = cursor.getString(7);
            dependenciaSalud.CodigoPostal = cursor.getString(8);
            dependenciaSalud.Telefono = cursor.getString(9);
            dependenciaSalud.Horario = cursor.getString(10);
            dependenciaSalud.AccionesSaludPublica = cursor.getString(11);
            dependenciaSalud.ConsultaMedicinaGeneralFamiliar = cursor.getString(12);
            dependenciaSalud.Odontologia = cursor.getString(13);
            dependenciaSalud.Anestesiologia = cursor.getString(14);
            dependenciaSalud.Cirugia = cursor.getString(15);
            dependenciaSalud.GinecologiaObstetricia = cursor.getString(16);
            dependenciaSalud.MedicinaInterna = cursor.getString(17);
            dependenciaSalud.Pediatra = cursor.getString(18);
            dependenciaSalud.TraumaOrtopedia = cursor.getString(19);
            dependenciaSalud.AtencionUrgencias = cursor.getString(20);
            dependenciaSalud.Radiologia = cursor.getString(21);
            dependenciaSalud.LaboratorioClinico = cursor.getString(22);
            dependenciaSalud.BancoSangre = cursor.getString(23);
            dependenciaSalud.Latitud = cursor.getString(24);
            dependenciaSalud.Longitud = cursor.getString(27);
            dependenciaSalud.Gestor = cursor.getString(25);
            dependenciaSalud.Unidad = cursor.getString(26);


            listaDS.add(dependenciaSalud);
        }

        if(getIntent().getIntExtra("tipoMapa",0) == 1)
        {
            lstBusqueda.setVisibility(View.VISIBLE);
            mAdapterBusqueda = new BusquedaAdapter(Busqueda.this, listaDS);
            lstBusqueda.setAdapter(mAdapterBusqueda);
        }
        else if(getIntent().getIntExtra("tipoMapa",0) == 2){
            lstBusquedaAfiliacion.setVisibility(View.VISIBLE);
            mAdapterAfiliacion = new AfiliacionAdapter(Busqueda.this, lista);
            lstBusquedaAfiliacion.setAdapter(mAdapterAfiliacion);
        }

        etxFindWarehouse.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(getIntent().getIntExtra("tipoMapa",0) == 1) {
                    if (!(mAdapterBusqueda == null))
                        mAdapterBusqueda.getFilter().filter(newText);
                }
                else if(getIntent().getIntExtra("tipoMapa",0) == 2) {
                    if (!(mAdapterAfiliacion == null))
                        mAdapterAfiliacion.getFilter().filter(newText);
                }
                return false;
            }
        });

        lstBusqueda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DependenciaSalud ds = (DependenciaSalud) lstBusqueda.getItemAtPosition(position);

                for(DependenciaSalud dependencia : listaDS){
                    if(ds.Nombre.equals(dependencia.Nombre)){
                        Intent detalles = new Intent(Busqueda.this,DependenciaSaludDetalles.class);
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

        lstBusquedaAfiliacion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CentroAfiliación ca = (CentroAfiliación) lstBusquedaAfiliacion.getItemAtPosition(position);

                for(CentroAfiliación afiliacion: lista){
                    if(ca.Nombre.equals(afiliacion.Nombre)){
                        Intent detalles = new Intent(Busqueda.this, DependenciaDetalles.class);
                        detalles.putExtra("name", afiliacion.Nombre);
                        detalles.putExtra("direccion", afiliacion.Direccion);
                        detalles.putExtra("telefono", afiliacion.Telefono);
                        detalles.putExtra("responsable", afiliacion.Responsable);
                        detalles.putExtra("telefonoResponsable", afiliacion.TelefonoResponsable);
                        detalles.putExtra("horario", afiliacion.Horario);
                        detalles.putExtra("correo", afiliacion.Correo);
                        detalles.putExtra("latitud", afiliacion.Latitud);
                        detalles.putExtra("longitud", afiliacion.Longitud);
                        startActivity(detalles);
                    }
                }

            }
        });
    }
}
