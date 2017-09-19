package com.repss.apprepss;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContainerActivity extends AppCompatActivity {

    SolicitudAtencionFragment solicitud = new SolicitudAtencionFragment();
    SintomasFragment sintomasFragment = new SintomasFragment();
    NotificacionesFragment notificacionesFragment = new NotificacionesFragment();
    FavoritosFragment favoritosFragment = new FavoritosFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);


        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(getIntent().getIntExtra("opcion",0) == 0){
            fragmentTransaction.replace(R.id.activity_container, solicitud);
        }else if(getIntent().getIntExtra("opcion",0) == 1){
            fragmentTransaction.replace(R.id.activity_container, notificacionesFragment);
        }else if(getIntent().getIntExtra("opcion",0) == 2){
            fragmentTransaction.replace(R.id.activity_container, sintomasFragment);
        }else if(getIntent().getIntExtra("opcion",0) == 3){
            fragmentTransaction.replace(R.id.activity_container, favoritosFragment);
        }

        fragmentTransaction.commit();
    }

    public void onBackPressed() {
        startActivity(new Intent(ContainerActivity.this,MainActivity.class));
    }
}
