package com.repss.apprepss;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MAOSFragment extends Fragment implements LocationListener {


    DbHelper helper;
    String nombre;
    int tipo = 1;
    MapView mapView;
    ArrayList<CentroAfiliación> lista = new ArrayList<CentroAfiliación>();
    Cursor cursor;
    CentroAfiliación centroAfiliación;
    MenuItem miSave;
    private LocationManager locationManager;
    double latitud,longitud;
    static boolean locationPermission;

    CameraPosition googlePlex;
    GoogleMap map;

    public MAOSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mao, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        if(locationPermission) {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    1000, 10, this);
        }

        if(lista.size() == 0) {

            helper = new DbHelper(getContext());

            final SQLiteDatabase db = helper.getReadableDatabase();

            cursor = db.rawQuery(" SELECT * FROM centros_afiliacion;", null);


            while (cursor.moveToNext()) {
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
        }

        mapView = (MapView)view.findViewById(R.id.centrosAfiliacionMapViewMAO);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                CameraPosition googlePlex;

                googlePlex = CameraPosition.builder()
                        .target(new LatLng(20.489394, -98.945058))
                        .zoom(8)
                        .build();

                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(googlePlex));



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        googleMap.setMyLocationEnabled(true);
                    }
                }
                else {
                    googleMap.setMyLocationEnabled(true);
                }

                for(CentroAfiliación centro : lista) {
                    if(centro.Nombre.equals("Coordinación Estatal Pachuca")){
                        googleMap.addMarker(new MarkerOptions().position(new LatLng(Float.valueOf(centro.Latitud), Float.valueOf(centro.Longitud))).title(centro.Nombre)).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                    }
                    else {
                        googleMap.addMarker(new MarkerOptions().position(new LatLng(Float.valueOf(centro.Latitud), Float.valueOf(centro.Longitud))).title(centro.Nombre)).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    }
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

                        for(CentroAfiliación centro : lista) {
                            if(centro.Nombre.equals(nombre)){
                                Intent detalles = new Intent(getContext(), DependenciaDetalles.class);
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

            }
        });



        return view;
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                //TODO:
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                //(just doing it here for now, note that with this code, no explanation is shown)
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

                locationPermission = false;


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
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
        inflater.inflate(R.menu.maps_menu, menu);
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
            if(map != null) {
                googlePlex = CameraPosition.builder()
                        .target(new LatLng(latitud, longitud))
                        .zoom(17)
                        .build();
                map.moveCamera(CameraUpdateFactory.newCameraPosition(googlePlex));
                locationManager.removeUpdates(this);
            }
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
