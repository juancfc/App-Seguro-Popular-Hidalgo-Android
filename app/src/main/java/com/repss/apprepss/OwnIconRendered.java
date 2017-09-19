package com.repss.apprepss;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import java.util.ArrayList;

/**
 * Created by maste on 09/03/2017.
 */


public class OwnIconRendered extends DefaultClusterRenderer<Items> {

    GoogleMap googleMap;
    ArrayList<DependenciaSalud> _listaDS;
    DbHelper helper;
    Cursor cursor;
    Context _context;
    DependenciaSalud dependenciaSalud;
    String _userClues;

    public OwnIconRendered(Context context, GoogleMap map, String userClues, ClusterManager<Items> clusterManager, ArrayList<DependenciaSalud> listaDS) {
        super(context, map, clusterManager);

        googleMap = map;
        _userClues = userClues;
        _listaDS = listaDS;
        _context = context;
    }

    /*public OwnIconRendered(Context context, GoogleMap map, ClusterManager<Items> clusterManager, ArrayList<DependenciaSalud> listaDS) {
        super(context, map, clusterManager);

        googleMap = map;
        _listaDS = listaDS;
        _context = context;
    }*/

    @Override
    protected void onBeforeClusterItemRendered(Items item, MarkerOptions markerOptions) {

            helper = new DbHelper(_context);

            final SQLiteDatabase db = helper.getReadableDatabase();

            cursor = db.rawQuery(" SELECT * FROM dependencias_salud WHERE latitud = " + String.valueOf(item.getPosition().latitude) + " AND longitud = " + String.valueOf(item.getPosition().longitude) + ";", null);

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


            }



        /*if(dependenciaSalud.Clues.equals(_userClues)) {
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        }
        else*/ if(dependenciaSalud.TipoEstablecimiento.equals("UNIDAD DE HOSPITALIZACIÓN")) {
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        }else if(dependenciaSalud.TipoEstablecimiento.equals("UNIDAD DE CONSULTA EXTERNA")){
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        }
        markerOptions.title(dependenciaSalud.Nombre);

        super.onBeforeClusterItemRendered(item, markerOptions);
    }
}
