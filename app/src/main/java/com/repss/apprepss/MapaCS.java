package com.repss.apprepss;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;

public class MapaCS extends AppCompatActivity implements OnMapReadyCallback {


    Toolbar myToolbar;
    BottomBar mBottomBar;
    GoogleMap googleMaps;
    CardView crdEstatus,crdInfoMarker;
    TextView txvEstatus,txvNombre,txvOpcionInfo, txvOpcionHistorial;
    Button btnDetalles;
    RelativeLayout rltLayoutMapa;
    LinearLayout lnrLayoutOpciones;
    DbHelper helper;
    CentrosBase centrosBase;
    DataBaseManager manager;
    Cursor cursor;
    CentroAfiliación centroAfiliación;
    DependenciaSalud dependenciaSalud;
    ArrayList<CentroAfiliación> lista = new ArrayList<CentroAfiliación>();
    ArrayList<DependenciaSalud> listaDS = new ArrayList<>();
    String nombre;
    int tipo;
    MenuItem miSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_cs);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        rltLayoutMapa = (RelativeLayout) findViewById(R.id.rltLayoutMapa);
        lnrLayoutOpciones = (LinearLayout)findViewById(R.id.lnrLayoutOpciones);
        setSupportActionBar(myToolbar);
        helper = new DbHelper(this);



        final SharedPreferences settings = getSharedPreferences("UserSettings", Context.MODE_PRIVATE);

        if(!settings.getBoolean("userMensaje",false)){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MapaCS.this);
            alertDialog.setCancelable(false);
            alertDialog.setTitle("Aviso");
            alertDialog.setMessage("No olvides asistir a tu consulta segura");
            alertDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.setNegativeButton("No volver a mostrar ", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("userMensaje",true);
                    editor.apply();
                }
            });
            alertDialog.show();
        }







        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.noTopOffset();
        mBottomBar.setMaxFixedTabs(3);

        mBottomBar.setItemsFromMenu(R.menu.bottombar_menuds, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottomBarItemDependenciasSalud) {
                    myToolbar.setBackgroundColor(getResources().getColor(R.color.azul));
                    tipo = 1;
                    if(googleMaps != null) {
                        rltLayoutMapa.setVisibility(View.VISIBLE);
                        lnrLayoutOpciones.setVisibility(View.GONE);
                        googleMaps.clear();
                        // The user selected item number one.
                        //rltLayout2.setVisibility(View.INVISIBLE);
                        //rltLayout1.setVisibility(View.VISIBLE);
                        for (DependenciaSalud dependencia : listaDS) {
                            googleMaps.addMarker(new MarkerOptions().position(new LatLng(Float.valueOf(dependencia.Latitud), Float.valueOf(dependencia.Longitud))).title(dependencia.Nombre)).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                        }
                    }


                }
                else if (menuItemId == R.id.bottomBarItemCentrosAfiliacion) {
                    myToolbar.setBackgroundColor(getResources().getColor(R.color.verde));
                    tipo = 2;
                    rltLayoutMapa.setVisibility(View.VISIBLE);
                    lnrLayoutOpciones.setVisibility(View.GONE);
                    googleMaps.clear();
                    // The user selected item number one.
                    //rltLayout1.setVisibility(View.INVISIBLE);
                    //rltLayout2.setVisibility(View.VISIBLE);
                    for(CentroAfiliación centro : lista) {
                        googleMaps.addMarker(new MarkerOptions().position(new LatLng(Float.valueOf(centro.Latitud), Float.valueOf(centro.Longitud))).title(centro.Nombre)).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    }
                }
                /*else if (menuItemId == R.id.bottomBarItemMas) {
                    tipo = 3;
                    lnrLayoutOpciones.setVisibility(View.VISIBLE);
                    rltLayoutMapa.setVisibility(View.GONE);
                }*/
            }
            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottomBarItemCentrosAfiliacion) {
                    myToolbar.setBackgroundColor(getResources().getColor(R.color.verde));
                    tipo = 2;
                    googleMaps.clear();
                    // The user selected item number one.
                    //rltLayout1.setVisibility(View.INVISIBLE);
                    //rltLayout2.setVisibility(View.VISIBLE);
                    for(CentroAfiliación centro : lista) {
                        googleMaps.addMarker(new MarkerOptions().position(new LatLng(Float.valueOf(centro.Latitud), Float.valueOf(centro.Longitud))).title(centro.Nombre)).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    }
                }
            }

        });


        /*mBo0.

        .0000000000000000ttomBar.setItemsFromMenu(R.menu.bottombar_menuds, new OnMenuTabClickListener() {
                    @Override
                    public void onMenuTabSelected(@IdRes int menuItemId) {

                    }

                    @Override
                    public void onMenuTabReSelected(@IdRes int menuItemId) {

                    }
                });*/
        mBottomBar.mapColorForTab(0, "#4CAF50");
        mBottomBar.mapColorForTab(1,"#4CAF50");
        mBottomBar.mapColorForTab(2, "#4CAF50");
        mBottomBar.mapColorForTab(3,"#4CAF50");
        //mBottomBar.mapColorForTab(2,"#00BCD4");





        Beneficiario beneficiario = new Beneficiario();
        //insertCA(MapaCS.this);



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



        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mfMapCS);
        mapFragment.getMapAsync(MapaCS.this);

        crdEstatus = (CardView)findViewById(R.id.crdEstatus);
        crdInfoMarker = (CardView)findViewById(R.id.crdInfoMarker);
        txvEstatus = (TextView) findViewById(R.id.txvEstatus);
        txvNombre = (TextView)findViewById(R.id.txvNombreMarker);
        txvOpcionInfo = (TextView)findViewById(R.id.txvOpcionInfo);
        txvOpcionHistorial = (TextView) findViewById(R.id.txvOpcionHistorial);
        btnDetalles = (Button)findViewById(R.id.btnDetalles);

        crdEstatus.setVisibility(View.VISIBLE);

        if (settings.getString("userStatus","").equals("Activo")){
            crdEstatus.setCardBackgroundColor(getResources().getColor(R.color.green));
        }else {
            crdEstatus.setCardBackgroundColor(getResources().getColor(R.color.red));
        }

        txvEstatus.setText(settings.getString("userStatus",""));

        crdEstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapaCS.this,UsuarioActivity.class));

            }
        });

        txvOpcionInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapaCS.this,UsuarioActivity.class));
            }
        });

        txvOpcionHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapaCS.this, DiarioMedico.class));
            }
        });

        /*btnDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(CentroAfiliación centro : lista) {
                    if(centro.id == Integer.valueOf(id)+1){
                        Intent detalles = new Intent(MapaCS.this, DependenciaDetalles.class);
                        detalles.putExtra("name", centro.Nombre);
                        detalles.putExtra("direccion", centro.Direccion);
                        detalles.putExtra("telefono", centro.Telefono);
                        detalles.putExtra("responsable", centro.Responsable);
                        detalles.putExtra("telefonoResponsable", centro.TelefonoResponsable);
                        detalles.putExtra("horario", centro.Horario);
                        detalles.putExtra("correo", centro.Correo);
                        detalles.putExtra("latitud", centro.Latitud);
                        detalles.putExtra("longitud", centro.Longitud);
                        startActivity(detalles);
                    }
                }
            }
        });
        */

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.maps_menu, menu);
        miSave = menu.findItem(R.id.action_search);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Intent contenido = new Intent(MapaCS.this, Busqueda.class);
                contenido.putExtra("tipoMapa",tipo);
                startActivity(contenido);
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMaps = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(20.47956, -98.88711))
                .zoom(8)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(googlePlex));



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                googleMap.setMyLocationEnabled(true);
            }
        }
        else {
            googleMap.setMyLocationEnabled(true);
        }


        for(DependenciaSalud dependencia : listaDS) {
            googleMaps.addMarker(new MarkerOptions().position(new LatLng(Float.valueOf(dependencia.Latitud), Float.valueOf(dependencia.Longitud))).title(dependencia.Nombre)).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        }


        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                nombre = marker.getTitle();
                //crdInfoMarker.setVisibility(View.VISIBLE);
                //marker.setTitle("");
                return false;
            }
        });

        /*googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                crdInfoMarker.setVisibility(View.INVISIBLE);
            }
        });*/

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if(tipo==1){
                    for(DependenciaSalud dependencia : listaDS){
                        if (dependencia.Nombre.equals(nombre)){
                            Intent detalles = new Intent(MapaCS.this,DependenciaSaludDetalles.class);
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
                else if (tipo == 2){
                    for(CentroAfiliación centro : lista) {
                        if(centro.Nombre.equals(nombre)){
                            Intent detalles = new Intent(MapaCS.this, DependenciaDetalles.class);
                            detalles.putExtra("name", centro.Nombre);
                            detalles.putExtra("direccion", centro.Direccion);
                            detalles.putExtra("telefono", centro.Telefono);
                            detalles.putExtra("responsable", centro.Responsable);
                            detalles.putExtra("telefonoResponsable", centro.TelefonoResponsable);
                            detalles.putExtra("horario", centro.Horario);
                            detalles.putExtra("correo", centro.Correo);
                            detalles.putExtra("latitud", centro.Latitud);
                            detalles.putExtra("longitud", centro.Longitud);
                            startActivity(detalles);
                        }
                    }
                }

            }
        });
    }



    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(startMain);
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                //TODO:
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                //(just doing it here for now, note that with this code, no explanation is shown)
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;


        }
    }
}

