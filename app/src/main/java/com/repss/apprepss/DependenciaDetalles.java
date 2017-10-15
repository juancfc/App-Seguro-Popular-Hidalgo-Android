package com.repss.apprepss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Se muestra un mapa con la ubicacion del centro deafiliación y muestra los detalle del mismo
 */
public class DependenciaDetalles extends AppCompatActivity implements OnMapReadyCallback {

    TextView txvNombre,txvDireccion,txvTelefono,txvResponsable,txvTelefonoResponsable,txvHorario,txvCorreo;

    String name,direccion,telefono,responsable,telefonoResponsable,horario,correo,latitud,longitud;

    int id;

    /**
     * Muestra los detalles del centro de afiliación  en la vista
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependencia_detalles);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mfMapDetalles);
        mapFragment.getMapAsync(DependenciaDetalles.this);



        name = getIntent().getStringExtra("name");
        direccion = getIntent().getStringExtra("direccion");
        telefono = getIntent().getStringExtra("telefono");
        responsable = getIntent().getStringExtra("responsable");
        telefonoResponsable = getIntent().getStringExtra("telefonoResponsable");
        horario = getIntent().getStringExtra("horario");
        correo = getIntent().getStringExtra("correo");
        latitud = getIntent().getStringExtra("latitud");
        longitud = getIntent().getStringExtra("longitud");

        txvNombre = (TextView)findViewById(R.id.txvNombre);
        txvDireccion = (TextView)findViewById(R.id.txvDireccion);
        txvTelefono = (TextView)findViewById(R.id.txvTelefono);
        txvResponsable = (TextView)findViewById(R.id.txvResponsable);
        txvTelefonoResponsable = (TextView)findViewById(R.id.txvTelefonoResponsable);
        txvHorario = (TextView)findViewById(R.id.txvHorario);
        txvCorreo = (TextView)findViewById(R.id.txvCorreo);

        txvNombre.setText("Nombre: " + name);
        txvDireccion.setText("Dirección: " + direccion);
        txvTelefono.setText("Teléfono: " + telefono);
        txvResponsable.setText("Responsable: " + responsable);
        txvTelefonoResponsable.setText("Teléfono responsable: " + telefonoResponsable);
        txvHorario.setText("Horario: " + horario);
        txvCorreo.setText("Correo: " + correo);


    }


    /**
     * Se muestra ek mapa con la ubiaccion del centro de afiliación y agrega un marcador
     * @param googleMap
     */
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



        Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(Float.valueOf(latitud), Float.valueOf(longitud))).title(name));


    }


}
