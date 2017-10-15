package com.repss.apprepss;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Muestra los detalles de la notificación
 */
public class NotificacionDetalles extends AppCompatActivity {

    TextView txvCardViewTitulo, txvCardViewDescripcion;
    String titulo, descripcion, link;
    ImageView imvFacebook,imvShare;



    DbHelper helper;
    DataBaseManager manager;


    /**
     * Se muestra la información en los controles de la vista
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion_detalles);

        final SharedPreferences settings = getSharedPreferences("UserSettings", Context.MODE_PRIVATE);

        titulo = getIntent().getStringExtra("Titulo");
        descripcion = getIntent().getStringExtra("Descripcion");
        link = getIntent().getStringExtra("Link");

        txvCardViewTitulo = (TextView)findViewById(R.id.txvCardViewTitulo);
        txvCardViewDescripcion = (TextView)findViewById(R.id.txvCardViewDescripcion);

        txvCardViewTitulo.setText(titulo);
        txvCardViewDescripcion.setText(descripcion);

        imvShare = (ImageView) findViewById(R.id.imvShare);

        if(getIntent().getBooleanExtra("NuevaNotificacion",false)) {

            manager = new DataBaseManager(getApplicationContext());

            manager.InsertNotificaciones(txvCardViewTitulo.getText().toString(), txvCardViewDescripcion.getText().toString(), settings.getString("userFolio", ""));
        }

        imvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, txvCardViewTitulo.getText().toString() + ". " + txvCardViewDescripcion.getText().toString());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share text"));
            }
        });

    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
