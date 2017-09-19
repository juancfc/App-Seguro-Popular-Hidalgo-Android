package com.repss.apprepss;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DependenciaSaludDetalles extends AppCompatActivity implements OnMapReadyCallback {


    DbHelper helper;
    Cursor cursor;
    DataBaseManager manager;

    ExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    TextView txvNombreSalud,txvCluesSalud,txvClaveSalud,txvMunicipioSalud,txvLocalidadSalud,txvTipoEstablecimientoSalud,txvDireccionSalud,txvCodigoPostalSalud,txvTelefonoSalud,txvHorarioSalud,txvServicio1,txvServicio2,txvServicio3,txvServicio4,txvServicio5,txvServicio6,txvServicio7,txvServicio8,txvServicio9,txvServicio10,txvServicio11,txvServicio12,txvServicio13,txvGestor,txvUnidad;

    String nombre,clues,clave,municipio,localidad,tipoEstablecimiento,direccion,codigoPostal,telefono,horario,latitud,longitud,servicio1,servicio2,servicio3,servicio4,servicio5,servicio6,servicio7,servicio8,servicio9,servicio10,servicio11,servicio12,servicio13,gestor,unidad;

    Button infoGestorButton;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependencia_salud_detalles);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mfMapDetallesSalud);
        mapFragment.getMapAsync(DependenciaSaludDetalles.this);


        id = getIntent().getIntExtra("id",0);
        nombre = getIntent().getStringExtra("nombre");
        clues = getIntent().getStringExtra("clues");
        clave = getIntent().getStringExtra("clave");
        municipio = getIntent().getStringExtra("municipio");
        localidad = getIntent().getStringExtra("localidad");
        tipoEstablecimiento = getIntent().getStringExtra("tipoEstablecimiento");
        direccion = getIntent().getStringExtra("direccion");
        codigoPostal = getIntent().getStringExtra("codigoPostal");
        telefono = getIntent().getStringExtra("telefono");
        horario = getIntent().getStringExtra("horario");
        latitud = getIntent().getStringExtra("latitud");
        longitud = getIntent().getStringExtra("longitud");
        servicio1 = getIntent().getStringExtra("servicio1");
        servicio2 = getIntent().getStringExtra("servicio2");
        servicio3 = getIntent().getStringExtra("servicio3");
        servicio4 = getIntent().getStringExtra("servicio4");
        servicio5 = getIntent().getStringExtra("servicio5");
        servicio6 = getIntent().getStringExtra("servicio6");
        servicio7 = getIntent().getStringExtra("servicio7");
        servicio8 = getIntent().getStringExtra("servicio8");
        servicio9 = getIntent().getStringExtra("servicio9");
        servicio10 = getIntent().getStringExtra("servicio10");
        servicio11 = getIntent().getStringExtra("servicio11");
        servicio12 = getIntent().getStringExtra("servicio12");
        servicio13 = getIntent().getStringExtra("servicio13");
        gestor = getIntent().getStringExtra("gestor");
        unidad = getIntent().getStringExtra("unidad");

        ExpandableListView elvInfo = (ExpandableListView)findViewById(R.id.elvInfo);
        infoGestorButton = (Button)findViewById(R.id.infoGestorButton);

        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        elvInfo.setAdapter(listAdapter);

        infoGestorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), InformacionGestor.class);
                i.putExtra("gestor",gestor);
                i.putExtra("unidad",unidad);
                startActivity(i);
            }
        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Información");
        //listDataHeader.add("Gestor Médico");
        listDataHeader.add("Servicios de salud");

        // Adding child data
        List<String> info = new ArrayList<String>();
        info.add("Nombre: " + nombre);
        info.add("Clues: " + clues);
        info.add("Clave: " + clave);
        info.add("Municipio: " + municipio);
        info.add("Localidad: " + localidad);
        info.add("Tipo de establecimiento: " + tipoEstablecimiento);
        info.add("Dirección: " + direccion);
        info.add("Código postal: " + codigoPostal);
        info.add("Teléfono: " + telefono);
        info.add("Horario(Matutino, Vespertino, Nocturno, Jornada Acumulada , Todos los anteriores): " + horario);

        /*List<String> gestorMedico = new ArrayList<String>();
        if(gestor != null || !gestor.equals("")) {
            gestorMedico.add("Gestor: " + gestor);
        }
        else
        {
            gestorMedico.add("Gestor: Actualmente no existe un gestor a cargo de esta unidad");
        }
        //gestorMedico.add("Unidad de adscripción: " + unidad);*/

        List<String> servicios = new ArrayList<String>();
        servicios.add("Acciones de Salud Pública: " + servicio1);
        servicios.add("Consulta de Medicina General / Familiar: " + servicio2);
        servicios.add("Odontología: " + servicio3);
        servicios.add("Anestesiología: " + servicio4);
        servicios.add("Cirugía: " + servicio5);
        servicios.add("Ginecología y Obstetricia: " + servicio6);
        servicios.add("Medicina Interna: " + servicio7);
        servicios.add("Pediatría: " + servicio8);
        servicios.add("Trauma  y Ortopedia: " + servicio9);
        servicios.add("Atenciones en Urgencias: " + servicio10);
        servicios.add("Radiología: " + servicio11);
        servicios.add("Laboratorio Clínico: " + servicio12);
        servicios.add("Banco de sangre: " + servicio13);

        listDataChild.put(listDataHeader.get(0), info); // Header, Child data
        //listDataChild.put(listDataHeader.get(1), gestorMedico);
        listDataChild.put(listDataHeader.get(1), servicios);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setScrollGesturesEnabled(false);
        googleMap.getUiSettings().setZoomGesturesEnabled(false);
        googleMap.getUiSettings().setRotateGesturesEnabled(false);

        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(Float.valueOf(latitud), Float.valueOf(longitud)))
                .zoom(17)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(googlePlex));



        Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(Float.valueOf(latitud), Float.valueOf(longitud))).title(nombre));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fav_menu, menu);
        MenuItem item = menu.findItem(R.id.favorito);
        helper = new DbHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery(" SELECT * FROM favoritos WHERE _id_unidad = "+ id + ";", null);

        if(cursor.getCount() != 0) {
            item.setIcon(R.mipmap.ic_favorite_red);

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorito:
                helper = new DbHelper(this);
                SQLiteDatabase db = helper.getReadableDatabase();
                cursor = db.rawQuery(" SELECT * FROM favoritos WHERE _id_unidad = "+ id + ";", null);

                if(cursor.getCount() == 0)
                {
                    manager = new DataBaseManager(getApplicationContext());

                    manager.InsertFavoritos(id);

                    Toast.makeText(this,"Se agrego la unidad de salud a favoritos",Toast.LENGTH_LONG).show();
                    item.setIcon(R.mipmap.ic_favorite_red);
                }
                else
                {
                    db.execSQL("DELETE FROM favoritos WHERE _id_unidad = "+ id + ";");
                    Toast.makeText(this,"Se eliminó de la lista de favoritos",Toast.LENGTH_LONG).show();
                    item.setIcon(R.mipmap.ic_favorite);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
