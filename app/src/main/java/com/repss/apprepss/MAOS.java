package com.repss.apprepss;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

/**
 * Muestra el fragmento con el mapa de los centros de afiliación
 */
public class MAOS extends AppCompatActivity {


    /**
     * Muestra el fragmento
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maos);


        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainMAOSLinearLayout, new MAOSFragment());
        fragmentTransaction.commit();

    }
}
