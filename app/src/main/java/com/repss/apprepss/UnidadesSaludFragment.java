package com.repss.apprepss;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;

public class UnidadesSaludFragment extends Fragment implements LocationListener {

    DbHelper helper;
    String nombre;
    int tipo = 1;
    MapView mapView;
    ArrayList<DependenciaSalud> listaDS = new ArrayList<>();
    Cursor cursor;
    DependenciaSalud dependenciaSalud;
    Toolbar myToolbar;
    MenuItem miSave;
    private LocationManager locationManager;
    double latitud,longitud;
    static boolean locationPermission;
    CameraPosition googlePlex;
    GoogleMap map;

    public UnidadesSaludFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_unidades_salud, container, false);

        SharedPreferences settings = getActivity().getSharedPreferences("UserSettings", Context.MODE_PRIVATE);

        final String userClues = settings.getString("userCLUES",null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        if(locationPermission) {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    3000, 0, this);
        }

        if(listaDS.size() == 0) {

            helper = new DbHelper(getContext());

            final SQLiteDatabase db = helper.getReadableDatabase();

            cursor = db.rawQuery(" SELECT * FROM dependencias_salud;", null);

            while(cursor.moveToNext()) {
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
        }

        mapView = (MapView)view.findViewById(R.id.unidadesSaludMapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                ClusterManager<Items> mClusterManager;

                mClusterManager = new ClusterManager<Items>(getContext(), googleMap);

                googleMap.setOnCameraIdleListener(mClusterManager);

                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);




                googlePlex = CameraPosition.builder()
                        .target(new LatLng(20.489394, -98.945058))
                        .zoom(8)
                        .build();


                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(googlePlex));



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        googleMap.setMyLocationEnabled(true);
                    }
                }
                else {
                    googleMap.setMyLocationEnabled(true);
                }


                for(DependenciaSalud dependencia : listaDS) {
                    /*if(dependencia.TipoEstablecimiento.equals("UNIDAD DE HOSPITALIZACIÓN"))
                        googleMap.addMarker(new MarkerOptions().position(new LatLng(Float.valueOf(dependencia.Latitud), Float.valueOf(dependencia.Longitud))).title(dependencia.Nombre)).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    else if (dependencia.TipoEstablecimiento.equals("UNIDAD DE CONSULTA EXTERNA"))
                        googleMap.addMarker(new MarkerOptions().position(new LatLng(Float.valueOf(dependencia.Latitud), Float.valueOf(dependencia.Longitud))).title(dependencia.Nombre)).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                        */


                    Items offsetItem = new Items(Double.valueOf(dependencia.Latitud), Double.valueOf(dependencia.Longitud));
                    mClusterManager.addItem(offsetItem);


                }

                mClusterManager.setRenderer(new OwnIconRendered(getContext(), googleMap, userClues, mClusterManager, listaDS));



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

                        for(DependenciaSalud dependencia : listaDS){
                            if (dependencia.Nombre.equals(nombre)){
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



            }
        });



        return view;
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {


                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

                locationPermission = false;


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

                locationPermission = false;

            }
            return false;
        } else {
            locationPermission = true;
            return true;


        }
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        miSave = menu.findItem(R.id.action_search);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Intent contenido = new Intent(getContext(), Busqueda.class);
                contenido.putExtra("tipoMapa",1);
                startActivity(contenido);
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {
        latitud = location.getLatitude();
        longitud = location.getLongitude();

        if (!(latitud == 0 && longitud == 0)) {
            googlePlex = CameraPosition.builder()
                    .target(new LatLng(latitud, longitud))
                    .zoom(17)
                    .build();
            map.moveCamera(CameraUpdateFactory.newCameraPosition(googlePlex));
            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {

    }

}