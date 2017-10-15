package com.repss.apprepss;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

/**
 * Se listan los sintomas guardados en la bd
 */
public class ListaSintomas extends AppCompatActivity {

    BottomBar mBottomBar;

    /**
     * Control de los tabs para mostrar síntomas físicos o emocionales
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sintomas);


        final Fragment fragmentUNO = new SintomasFisicosFragment();
        final Fragment fragmentDOS = new SintomasEmocionalesFragment();

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.noTopOffset();
        mBottomBar.setMaxFixedTabs(1);
        mBottomBar.setItemsFromMenu(R.menu.bottombar_sintomas, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                FragmentTransaction FT = getSupportFragmentManager().beginTransaction();
                if (menuItemId == R.id.bottomBarItemFisico) {
                    FT.replace(R.id.containerSintomas, fragmentUNO);
                }
                else if (menuItemId == R.id.bottomBarItemEmocional) {
                    FT.replace(R.id.containerSintomas, fragmentDOS);
                }

                FT.commit();
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
            }
        });
        mBottomBar.mapColorForTab(0,"#ba0c2f");
        mBottomBar.mapColorForTab(1,"#ba0c2f");

    }

    /**
     * Evento parala navegacion al momento de presionar el botón back
     */
    @Override
    public void onBackPressed() {
        startActivity(new Intent(ListaSintomas.this,ContainerActivity.class).putExtra("opcion", 2));
    }
}
