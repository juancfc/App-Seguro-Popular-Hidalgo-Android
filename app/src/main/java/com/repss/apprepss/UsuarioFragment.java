package com.repss.apprepss;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.*;
import android.support.v4.BuildConfig;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.microsoft.windowsazure.messaging.NotificationHub;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.io.File;
import java.util.ArrayList;


public class UsuarioFragment extends Fragment {

    TextView txvUserFolio,txvUserConsecutivo,txvUserNombres,txvUserApellidoPaterno,txvUserApellidoMaterno,txvUserCurp,txvUserDependenciaSalud, txvUserFechaVencimiento, txvUserSexo,txvUserEdad;
    Button btnTomarCaptura,btnVerPoliza,btnCauses;

    private MobileServiceClient mClient;
    MobileServiceTable<Notificaciones> mTableNotificaciones;
    MobileServiceList<Notificaciones> listNotificaciones;
    NotificacionesAdapter mAdapter;
    ListView lstNotificaciones;

    AppSettings objAppSettings = new AppSettings();

    DbHelper helper;
    Cursor cursor;
    DependenciaSalud dependenciaSalud;
    ArrayList<DependenciaSalud> listaDS = new ArrayList<>();

    ArrayList<Notificacion> listaNotificaciones = new ArrayList<Notificacion>();

    private static final int CONTENT_REQUEST=1337;
    private File output=null;

    public UsuarioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usuario, container, false);

        int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);

        final SharedPreferences settings = getActivity().getSharedPreferences("UserSettings", Context.MODE_PRIVATE);

        Button btnRequisitos = (Button)view.findViewById(R.id.btnRequisitos);
        btnTomarCaptura = (Button)view.findViewById(R.id.btnTomarCaptura);
        btnVerPoliza = (Button)view.findViewById(R.id.btnVerPoliza);
        btnCauses = (Button)view.findViewById(R.id.btnCauses);

        //Usuario
        txvUserFolio = (TextView)view.findViewById(R.id.txvUserFolio);
        txvUserConsecutivo = (TextView)view.findViewById(R.id.txvUserConsecutivo);
        txvUserNombres = (TextView)view.findViewById(R.id.txvUserNombre);
        txvUserApellidoPaterno = (TextView)view.findViewById(R.id.txvUserApellidoPaterno);
        txvUserApellidoMaterno = (TextView)view.findViewById(R.id.txvUserApellidoMaterno);
        txvUserCurp = (TextView)view.findViewById(R.id.txvUserCurp);
        txvUserFechaVencimiento = (TextView)view.findViewById(R.id.txvUserFechaVencimiento);
        txvUserDependenciaSalud = (TextView)view.findViewById(R.id.txvUserDependenciaSalud);
        txvUserSexo = (TextView)view.findViewById(R.id.txvUserSexo);
        txvUserEdad = (TextView)view.findViewById(R.id.txvUserEdad);

        //Información de usuario
        txvUserFolio.setText(settings.getString("userFolio",""));
        txvUserConsecutivo.setText(settings.getString("userConsecutivo",""));
        txvUserNombres.setText(settings.getString("userNombres",""));
        txvUserApellidoPaterno.setText(settings.getString("userApellidoPaterno",""));
        txvUserApellidoMaterno.setText(settings.getString("userApellidoMaterno",""));
        txvUserCurp.setText(settings.getString("userCURP",""));
        txvUserFechaVencimiento.setText(settings.getString("userFechaVencimiento",""));
        txvUserDependenciaSalud.setText(
                Html.fromHtml("<u>" + settings.getString("userDependenciaSalud","") + "</u>"),
                TextView.BufferType.SPANNABLE);
        txvUserSexo.setText(settings.getString("userSexo",""));
        txvUserEdad.setText(settings.getString("userEdad",""));

        //Captura Póliza
        File dir=
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);


        output=new File(dir, "CameraContentDemo.jpeg");

        if(output.exists()){
            btnVerPoliza.setVisibility(View.VISIBLE);

        }
        else{
            btnTomarCaptura.setVisibility(View.VISIBLE);
        }

        //Visibilidad de boton Requisitos
        if (!settings.getString("userStatus","").equals("Activo")) {
            btnRequisitos.setVisibility(View.VISIBLE);
        }

        //Evento para visualizar la unidad de salud correspondiente del usuario
        txvUserDependenciaSalud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper = new DbHelper(getActivity());
                final SQLiteDatabase db = helper.getReadableDatabase();

                if(listaDS.size() == 0) {
                    cursor = db.rawQuery(" SELECT * FROM dependencias_salud;", null);

                    while (cursor.moveToNext()) {
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

                for (DependenciaSalud ds : listaDS){
                    if(settings.getString("userCLUES","").equals(ds.Clues)){
                        Intent detalles = new Intent(getContext(),DependenciaSaludDetalles.class);
                        detalles.putExtra("id",String.valueOf(ds.id));
                        detalles.putExtra("nombre",ds.Nombre);
                        detalles.putExtra("clues",ds.Clues);
                        detalles.putExtra("clave",ds.Clave);
                        detalles.putExtra("municipio",ds.Municipio);
                        detalles.putExtra("localidad",ds.Localidad);
                        detalles.putExtra("tipoEstablecimiento",ds.TipoEstablecimiento);
                        detalles.putExtra("direccion",ds.Direccion);
                        detalles.putExtra("codigoPostal",ds.CodigoPostal);
                        detalles.putExtra("telefono",ds.Telefono);
                        detalles.putExtra("horario",ds.Horario);
                        detalles.putExtra("latitud", ds.Latitud);
                        detalles.putExtra("longitud", ds.Longitud);
                        detalles.putExtra("servicio1", ds.AccionesSaludPublica);
                        detalles.putExtra("servicio2", ds.ConsultaMedicinaGeneralFamiliar);
                        detalles.putExtra("servicio3", ds.Odontologia);
                        detalles.putExtra("servicio4", ds.Anestesiologia);
                        detalles.putExtra("servicio5", ds.Cirugia);
                        detalles.putExtra("servicio6", ds.GinecologiaObstetricia);
                        detalles.putExtra("servicio7", ds.MedicinaInterna);
                        detalles.putExtra("servicio8", ds.Pediatra);
                        detalles.putExtra("servicio9", ds.TraumaOrtopedia);
                        detalles.putExtra("servicio10", ds.AtencionUrgencias);
                        detalles.putExtra("servicio11", ds.Radiologia);
                        detalles.putExtra("servicio12", ds.LaboratorioClinico);
                        detalles.putExtra("servicio13", ds.BancoSangre);
                        detalles.putExtra("gestor", ds.Gestor);
                        detalles.putExtra("unidad", ds.Unidad);
                        startActivity(detalles);
                    }
                }


            }
        });

        //Evento para poder capturar la foto de la póliza
        btnTomarCaptura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.CAMERA)) {

                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.

                        UsuarioFragment.this.requestPermissions(new String[]{Manifest.permission.CAMERA}, 101);

                    } else {

                        // No explanation needed, we can request the permission.

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            UsuarioFragment.this.requestPermissions(
                                    new String[]{Manifest.permission.CAMERA}, 101);
                        }

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                }
                else {
                    Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File dir=
                            getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);


                    output=new File(dir, "CameraContentDemo.jpeg");



                    //i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));
                    i.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(), getContext().getPackageName() +".provider", output));

                    startActivityForResult(i, CONTENT_REQUEST);
                }
            }
        });

        btnRequisitos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),RequisitosActivity.class));
            }
        });



        btnVerPoliza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File dir=
                        getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

                output=new File(dir, "CameraContentDemo.jpeg");

                Intent i=new Intent(Intent.ACTION_VIEW);

                //i.setDataAndType(Uri.fromFile(output), "image/jpeg");
                //i.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".provider", output));

                i.setDataAndType(FileProvider.getUriForFile(getContext(), getContext().getPackageName() + ".provider", output), "image/jpeg");
                i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(i);
            }
        });

        btnCauses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),BeneficiosActivity.class));
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            btnVerPoliza.setVisibility(View.VISIBLE);
            btnTomarCaptura.setVisibility(View.GONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 101: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getContext(),
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getContext(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {

                        // Should we show an explanation?
                        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                            // Show an explanation to the user *asynchronously* -- don't block
                            // this thread waiting for the user's response! After the user
                            // sees the explanation, try again to request the permission.

                            UsuarioFragment.this.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 102);

                        } else {

                            // No explanation needed, we can request the permission.

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                UsuarioFragment.this.requestPermissions(
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 102);
                            }

                            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                            // app-defined int constant. The callback method gets the
                            // result of the request.
                        }
                    }


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    Toast.makeText(getContext(), "Se necesita el permiso para poder tomar la foto", Toast.LENGTH_LONG);
                }
                return;
            }
            case 102: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File dir=
                            getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);


                    output=new File(dir, "CameraContentDemo.jpeg");



                    //i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));
                    i.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(), getContext().getPackageName() + ".provider", output));
                    startActivityForResult(i, CONTENT_REQUEST);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    Toast.makeText(getContext(), "Se necesita el permiso para poder tomar la foto", Toast.LENGTH_LONG);
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
