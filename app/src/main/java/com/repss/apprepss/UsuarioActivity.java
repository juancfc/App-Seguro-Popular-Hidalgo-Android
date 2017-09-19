package com.repss.apprepss;

import android.animation.Animator;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.html.HtmlEscapers;
import com.google.firebase.iid.FirebaseInstanceId;
import com.microsoft.windowsazure.messaging.NotificationHub;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.query.QueryOrder;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class UsuarioActivity extends AppCompatActivity {

    final Fragment fragment = new SintomasFragment();

    private MobileServiceClient mClient;
    MobileServiceTable<Notificaciones> mTableNotificaciones;
    MobileServiceList<Notificaciones> listNotificaciones;
    NotificacionesAdapter mAdapter;
    ListView lstNotificaciones;

    DbHelper helper;
    Cursor cursor;
    DependenciaSalud dependenciaSalud;
    ArrayList<DependenciaSalud> listaDS = new ArrayList<>();

    ArrayList<Notificacion> listaNotificaciones = new ArrayList<Notificacion>();

    private float scale = 1f;
    private ScaleGestureDetector SGD;

    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();

    // We can be in one of these 3 states
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    // Remember some things for zooming
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;


    static String path;
    BottomBar mBottomBar;
    TextView txvUserFolio,txvUserConsecutivo,txvUserNombres,txvUserApellidoPaterno,txvUserApellidoMaterno,txvUserCurp,txvUserDependenciaSalud, txvUserFechaVencimiento, txvUserSexo,txvUserEdad;
    EditText edtContacto, edtDenuncia;
    Button btnTomarCaptura,btnVerPoliza;
    ImageView imgPoliza, mPinchZoomImageView;
    AppSettings objAppSettings = new AppSettings();

    Animator mCurrentAnimator;
    int mLongAnimationDuration;

    private static final int CONTENT_REQUEST=1337;
    private File output=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);



        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Inicialización de la mobile app
        try {
            mClient = new MobileServiceClient(
                    "https://seguropopularapplication.azurewebsites.net",
                    this
            );

            mTableNotificaciones = mClient.getTable(Notificaciones.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Configuración del usuario
        final SharedPreferences settings = getSharedPreferences("UserSettings", Context.MODE_PRIVATE);

        //Tabs
        final LinearLayout rltLayout1 = (LinearLayout) findViewById(R.id.rltLayout1);
        final LinearLayout rltLayout2 = (LinearLayout) findViewById(R.id.rltLayout2);
        final LinearLayout rltLayout3 = (LinearLayout) findViewById(R.id.rltLayout3);

        //Buttons
        Button btnRequisitos = (Button)findViewById(R.id.btnRequisitos);
        Button btnEnviarDenuncia = (Button)findViewById(R.id.btnEnviarDenuncia);
        btnTomarCaptura = (Button)findViewById(R.id.btnTomarCaptura);
        btnVerPoliza = (Button)findViewById(R.id.btnVerPoliza);

        //Usuario
        txvUserFolio = (TextView)findViewById(R.id.txvUserFolio);
        txvUserConsecutivo = (TextView)findViewById(R.id.txvUserConsecutivo);
        txvUserNombres = (TextView)findViewById(R.id.txvUserNombre);
        txvUserApellidoPaterno = (TextView)findViewById(R.id.txvUserApellidoPaterno);
        txvUserApellidoMaterno = (TextView)findViewById(R.id.txvUserApellidoMaterno);
        txvUserCurp = (TextView)findViewById(R.id.txvUserCurp);
        txvUserFechaVencimiento = (TextView)findViewById(R.id.txvUserFechaVencimiento);
        txvUserDependenciaSalud = (TextView)findViewById(R.id.txvUserDependenciaSalud);
        txvUserSexo = (TextView)findViewById(R.id.txvUserSexo);
        txvUserEdad = (TextView)findViewById(R.id.txvUserEdad);


        //ListView
        final ArrayList<Notificaciones> m = new ArrayList<Notificaciones>();
        lstNotificaciones = (ListView)findViewById(R.id.lstNotificaciones);

        //Solicitud
        edtContacto = (EditText)findViewById(R.id.edtContacto);
        edtDenuncia = (EditText)findViewById(R.id.edtDenuncia);

        imgPoliza = (ImageView)findViewById(R.id.imgPoliza);
        mPinchZoomImageView = (ImageView)findViewById(R.id.pinchZoomImageView);

        //Información de usuario
        txvUserFolio.setText("Folio: " + settings.getString("userFolio",""));
        txvUserConsecutivo.setText("Número consecutivo: " + settings.getString("userConsecutivo",""));
        txvUserNombres.setText("Nombre: " + settings.getString("userNombres",""));
        txvUserApellidoPaterno.setText("Apellido paterno: " + settings.getString("userApellidoPaterno",""));
        txvUserApellidoMaterno.setText("Apellido materno: " + settings.getString("userApellidoMaterno",""));
        txvUserCurp.setText("CURP: " + settings.getString("userCURP",""));
        txvUserFechaVencimiento.setText("Fecha de Vencimiento: " + settings.getString("userFechaVencimiento",""));
        txvUserDependenciaSalud.setText(
                Html.fromHtml("<u>Dependencia de salud correspondiente: " + settings.getString("userDependenciaSalud","") + "</u>"),
                TextView.BufferType.SPANNABLE);
        txvUserSexo.setText("Sexo: " + settings.getString("userSexo",""));
        txvUserEdad.setText("Edad: " + settings.getString("userEdad",""));

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
                helper = new DbHelper(UsuarioActivity.this);
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
                        Intent detalles = new Intent(UsuarioActivity.this,DependenciaSaludDetalles.class);
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
                Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File dir=
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);


                output=new File(dir, "CameraContentDemo.jpeg");



                    i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));

                    startActivityForResult(i, CONTENT_REQUEST);

            }
        });

        btnRequisitos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UsuarioActivity.this,RequisitosActivity.class));
            }
        });



        btnEnviarDenuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtDenuncia.getText().toString().equals("")){
                    SendMail sendMail = null;
                    if (!edtContacto.getText().toString().equals(""))
                        //Toast.makeText(UsuarioActivity.this, "Hubo un error, favor de intentar de nuevo más tarde", Toast.LENGTH_SHORT).show();
                        sendMail = new SendMail(UsuarioActivity.this,"desarrolloprospectiva@gmail.com","Denuncia App REPSS",edtDenuncia.getText().toString() + "\n\n" + "Información de contacto: " + edtContacto.getText().toString(),edtContacto,edtDenuncia);
                    else
                        //Toast.makeText(UsuarioActivity.this, "Error, favor de intentar de nuevo más tarde", Toast.LENGTH_SHORT).show();
                        sendMail = new SendMail(UsuarioActivity.this,"desarrolloprospectiva@gmail.com","Denuncia App REPSS",edtDenuncia.getText().toString(),edtContacto,edtDenuncia);

                    sendMail.execute();

                }else {
                    edtDenuncia.setError("Este campo no puede quedar vacío");
                }

            }
        });

        mLongAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);

        btnVerPoliza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File dir=
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

                output=new File(dir, "CameraContentDemo.jpeg");

                Intent i=new Intent(Intent.ACTION_VIEW);

                i.setDataAndType(Uri.fromFile(output), "image/jpeg");
                startActivity(i);
            }
        });

        imgPoliza.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                File dir=
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

                output=new File(dir, "CameraContentDemo.jpeg");

                Intent i=new Intent(Intent.ACTION_VIEW);

                i.setDataAndType(Uri.fromFile(output), "image/jpeg");
                startActivity(i);

                return true;
            }
        });

        mPinchZoomImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPinchZoomImageView.setVisibility(View.INVISIBLE);
                imgPoliza.setVisibility(View.VISIBLE);
            }
        });

        lstNotificaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Notificacion not = (Notificacion)lstNotificaciones.getItemAtPosition(position);

                Intent detalles = new Intent(UsuarioActivity.this,NotificacionDetalles.class);
                detalles.putExtra("Titulo",not.titulo);
                detalles.putExtra("Descripcion",not.descripcion);
                detalles.putExtra("NuevaNotificacion",false);
                startActivity(detalles);
            }
        });





        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.noTopOffset();
        mBottomBar.setMaxFixedTabs(3);
        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {

                android.support.v4.app.FragmentManager FM = getSupportFragmentManager();
                final android.support.v4.app.FragmentTransaction FT = FM.beginTransaction();

                if (menuItemId == R.id.bottomBarItemInfo) {
                    // The user selected item number one.
                    rltLayout3.setVisibility(View.INVISIBLE);
                    rltLayout2.setVisibility(View.INVISIBLE);
                    rltLayout1.setVisibility(View.VISIBLE);
                    FT.remove(fragment);
                }
                else if (menuItemId == R.id.bottomBarItemDenuncias) {
                    // The user selected item number one.
                    rltLayout3.setVisibility(View.INVISIBLE);
                    rltLayout1.setVisibility(View.INVISIBLE);
                    rltLayout2.setVisibility(View.VISIBLE);
                    FT.remove(fragment);

                }
                else if (menuItemId == R.id.bottomBarItemNotificaciones) {
                    // The user selected item number one.
                    rltLayout2.setVisibility(View.INVISIBLE);
                    rltLayout1.setVisibility(View.INVISIBLE);
                    rltLayout3.setVisibility(View.VISIBLE);
                    FT.remove(fragment);

                    helper = new DbHelper(UsuarioActivity.this);
                    final SQLiteDatabase db = helper.getReadableDatabase();

                    try {
                        cursor = db.rawQuery(" SELECT * FROM notificaciones WHERE poliza=" + settings.getString("userFolio", "") + ";", null);
                    }
                    catch (Exception ex){
                        Log.e("TAG",ex.toString());
                    }
                    if(listaNotificaciones.isEmpty()){
                        while (cursor.moveToNext()) {
                            Notificacion notificacion = new Notificacion();
                            notificacion.id = Integer.valueOf(cursor.getString(0));
                            notificacion.titulo = cursor.getString(1);
                            notificacion.descripcion = cursor.getString(2);
                            notificacion.poliza = cursor.getString(3);

                            listaNotificaciones.add(notificacion);
                        }
                    }
                    mAdapter = new NotificacionesAdapter(UsuarioActivity.this,listaNotificaciones);
                    lstNotificaciones.setAdapter(mAdapter);
                }
                else if (menuItemId == R.id.bottomBarItemSintomas) {
                    // The user selected item number one.
                    rltLayout2.setVisibility(View.INVISIBLE);
                    rltLayout1.setVisibility(View.INVISIBLE);
                    rltLayout3.setVisibility(View.INVISIBLE);
                    FT.add(R.id.container, fragment);

                }
                    FT.commit();
            }
            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottomBarItemDenuncias) {
                    // The user selected item number one.
                    rltLayout1.setVisibility(View.INVISIBLE);
                    rltLayout2.setVisibility(View.VISIBLE);
                }
            }
        });

        mBottomBar.mapColorForTab(0,"#4CAF50");
        mBottomBar.mapColorForTab(1,"#F44336");
        mBottomBar.mapColorForTab(2,"#092432");
        mBottomBar.mapColorForTab(3,"#00BCD4");
        mBottomBar.selectTabAtPosition(getIntent().getIntExtra("Tab",0),false);
    }

    private void loadImageFromStorage(String path) {

    }


    private String saveToInternalStorage(Bitmap bitmapImage) throws IOException {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fos.close();
        }
        return directory.getAbsolutePath();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            btnVerPoliza.setVisibility(View.VISIBLE);
            btnTomarCaptura.setVisibility(View.GONE);
        }
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(UsuarioActivity.this,MapaCS.class));
    }
}
