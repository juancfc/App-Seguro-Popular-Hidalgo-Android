package com.repss.apprepss;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CausesActivity extends AppCompatActivity {

    Toolbar causesToolbar;
    ExpandableListAdapter listAdapter;

    MenuItem search;
    Constants constants;
    SimpleListAdapter mAdapter;
    ExpandableListView elvCauses;
    ListView lstCauses;

    List<String> listDataHeaderCauses;
    HashMap<String, List<String>> listDataChildCauses;

    List<String> listDataHeaderGastosCatastroficos;
    HashMap<String, List<String>> listDataChildGastosCatastroficos;

    List<String> listDataHeaderSeguroMedico;
    HashMap<String, List<String>> listDataChildSeguroMedico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_causes);



        constants = new Constants();

        elvCauses = (ExpandableListView)findViewById(R.id.elvCauses);
        lstCauses = (ListView)findViewById(R.id.lstCauses);

        int opcionBeneficios = getIntent().getIntExtra("beneficios",0);

        if(opcionBeneficios == 1){
            prepareListData(constants.GetCauses(),opcionBeneficios);
            listAdapter = new ExpandableListAdapter(this, listDataHeaderCauses, listDataChildCauses);
            mAdapter = new SimpleListAdapter(this,constants.GetIntervencionesCauses());
        }else if(opcionBeneficios == 2){
            prepareListData(constants.GetGastosCatastroficos(),opcionBeneficios);
            listAdapter = new ExpandableListAdapter(this, listDataHeaderGastosCatastroficos, listDataChildGastosCatastroficos);
            mAdapter = new SimpleListAdapter(this,constants.GetIntervencionesGastosCatastroficos());
        }else if(opcionBeneficios == 3){
            prepareListData(constants.GetSeguroMedico(),opcionBeneficios);
            listAdapter = new ExpandableListAdapter(this, listDataHeaderSeguroMedico, listDataChildSeguroMedico);
            mAdapter = new SimpleListAdapter(this,constants.GetIntervencionesSeguroMedico());
        }

        elvCauses.setAdapter(listAdapter);
        lstCauses.setAdapter(mAdapter);
    }

    private void prepareListData(ArrayList<Causes> listaBeneficios, int opcion) {

        listDataHeaderCauses = new ArrayList<String>();
        listDataChildCauses = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeaderCauses.add("Intervenciones de salud pública");
        listDataHeaderCauses.add("Intervenciones de atención de medicina general, familiar y especialidad");
        listDataHeaderCauses.add("Intervenciones de odontología");
        listDataHeaderCauses.add("Intervenciones en urgencias");
        listDataHeaderCauses.add("Intervenciones en hospitalización");
        listDataHeaderCauses.add("Intervenciones de cirugía general");

        listDataHeaderGastosCatastroficos = new ArrayList<String>();
        listDataChildGastosCatastroficos = new HashMap<String, List<String>>();

        listDataHeaderGastosCatastroficos.add("UCIN");
        listDataHeaderGastosCatastroficos.add("Malformaciones congénitas, quirúrgicas  y adquiridas");
        listDataHeaderGastosCatastroficos.add("Enfermedades metabólicas");
        listDataHeaderGastosCatastroficos.add("Cáncer  en menores de 18 años");
        listDataHeaderGastosCatastroficos.add("Cáncer  en mayores de 18 años");
        listDataHeaderGastosCatastroficos.add("Menores  de 60 años");
        listDataHeaderGastosCatastroficos.add("20 a 50 años");
        listDataHeaderGastosCatastroficos.add("Paciente  pediátrico y adulto");

        listDataHeaderSeguroMedico = new ArrayList<String>();
        listDataChildSeguroMedico = new HashMap<String, List<String>>();

        listDataHeaderSeguroMedico.add("Ciertas  enfermedades infecciosas y parasitarias");
        listDataHeaderSeguroMedico.add("Tumores");
        listDataHeaderSeguroMedico.add("Enfermedades de la sangre y de los órganos hematopoyéticos y ciertos trastornos que afectan  el mecanismo de la inmunidad");
        listDataHeaderSeguroMedico.add("Enfermedades endocrinas, nutricionales y metabólicas");
        listDataHeaderSeguroMedico.add("Enfermedades del sistema nervioso");
        listDataHeaderSeguroMedico.add("Enfermedades del ojo");
        listDataHeaderSeguroMedico.add("Enfermedades del oído");
        listDataHeaderSeguroMedico.add("Enfermedades del sistema circulatorio");
        listDataHeaderSeguroMedico.add("Enfermedades del sistema respiratorio");
        listDataHeaderSeguroMedico.add("Enfermedades del sistema digestivo");
        listDataHeaderSeguroMedico.add("Enfermedades de la piel");
        listDataHeaderSeguroMedico.add("Enfermedades del sistema osteomuscular");
        listDataHeaderSeguroMedico.add("Poliarteritis nodosa y afecciones relacionadas");
        listDataHeaderSeguroMedico.add("Enfermedades del sistema genitourinario");
        listDataHeaderSeguroMedico.add("Ciertas  afecciones originadas  en el periodo perinatal");
        listDataHeaderSeguroMedico.add("Malformaciones congénitas, deformidades y anomalías cromosómicas");
        listDataHeaderSeguroMedico.add("Síntomas  y signos generales");
        listDataHeaderSeguroMedico.add("Traumatismos, envenenamientos y algunas otras consecuencias de causas externas");
        listDataHeaderSeguroMedico.add("Quemaduras y corrosiones");
        listDataHeaderSeguroMedico.add("Complicaciones de la atención médica y quirúrgica");
        listDataHeaderSeguroMedico.add("Factores que influyen  en el estado de salud y contacto  con los servicios de salud");




        // Adding child data
        List<String> subItem1 = new ArrayList<String>();
        List<String> subItem2 = new ArrayList<String>();
        List<String> subItem3 = new ArrayList<String>();
        List<String> subItem4 = new ArrayList<String>();
        List<String> subItem5 = new ArrayList<String>();
        List<String> subItem6 = new ArrayList<String>();


        List<String> subItem7 = new ArrayList<String>();
        List<String> subItem8 = new ArrayList<String>();
        List<String> subItem9 = new ArrayList<String>();
        List<String> subItem10 = new ArrayList<String>();
        List<String> subItem11 = new ArrayList<String>();
        List<String> subItem12 = new ArrayList<String>();
        List<String> subItem13 = new ArrayList<String>();
        List<String> subItem14 = new ArrayList<String>();


        List<String> subItem15 = new ArrayList<String>();
        List<String> subItem16 = new ArrayList<String>();
        List<String> subItem17 = new ArrayList<String>();
        List<String> subItem18 = new ArrayList<String>();
        List<String> subItem19 = new ArrayList<String>();
        List<String> subItem20 = new ArrayList<String>();
        List<String> subItem21 = new ArrayList<String>();
        List<String> subItem22 = new ArrayList<String>();
        List<String> subItem23 = new ArrayList<String>();
        List<String> subItem24 = new ArrayList<String>();
        List<String> subItem25 = new ArrayList<String>();
        List<String> subItem26 = new ArrayList<String>();
        List<String> subItem27 = new ArrayList<String>();
        List<String> subItem28 = new ArrayList<String>();
        List<String> subItem29 = new ArrayList<String>();
        List<String> subItem30 = new ArrayList<String>();
        List<String> subItem31 = new ArrayList<String>();
        List<String> subItem32 = new ArrayList<String>();
        List<String> subItem33 = new ArrayList<String>();
        List<String> subItem34 = new ArrayList<String>();
        List<String> subItem35 = new ArrayList<String>();


        for(Causes causes : listaBeneficios){
            switch (causes.tipo){
                case "Intervenciones de salud pública":
                    subItem1.add(causes.intervencion);
                    break;
                case "Intervenciones de atención de medicina general, familiar y especialidad":
                    subItem2.add(causes.intervencion);
                    break;
                case "Intervenciones de odontología":
                    subItem3.add(causes.intervencion);
                    break;
                case "Intervenciones en urgencias":
                    subItem4.add(causes.intervencion);
                    break;
                case "Intervenciones en hospitalización":
                    subItem5.add(causes.intervencion);
                    break;
                case "Intervenciones de cirugía general":
                    subItem6.add(causes.intervencion);
                    break;
                case "UCIN":
                    subItem7.add(causes.intervencion);
                    break;
                case "Malformaciones congénitas, quirúrgicas  y adquiridas":
                    subItem8.add(causes.intervencion);
                    break;
                case "Enfermedades metabólicas":
                    subItem9.add(causes.intervencion);
                    break;
                case "Cáncer  en menores de 18 años":
                    subItem10.add(causes.intervencion);
                    break;
                case "Cáncer  en mayores de 18 años":
                    subItem11.add(causes.intervencion);
                    break;
                case "Menores  de 60 años":
                    subItem12.add(causes.intervencion);
                    break;
                case "20 a 50 años":
                    subItem13.add(causes.intervencion);
                    break;
                case "Paciente  pediátrico y adulto":
                    subItem14.add(causes.intervencion);
                    break;
                case "Ciertas  enfermedades infecciosas y parasitarias":
                    subItem15.add(causes.intervencion);
                    break;
                case "Tumores":
                    subItem16.add(causes.intervencion);
                    break;
                case "Enfermedades de la sangre y de los órganos hematopoyéticos y ciertos trastornos que afectan  el mecanismo de la inmunidad":
                    subItem17.add(causes.intervencion);
                    break;
                case "Enfermedades endocrinas, nutricionales y metabólicas":
                    subItem18.add(causes.intervencion);
                    break;
                case "Enfermedades del sistema nervioso":
                    subItem19.add(causes.intervencion);
                    break;
                case "Enfermedades del ojo":
                    subItem20.add(causes.intervencion);
                    break;
                case "Enfermedades del oído":
                    subItem21.add(causes.intervencion);
                    break;
                case "Enfermedades del sistema circulatorio":
                    subItem22.add(causes.intervencion);
                    break;
                case "Enfermedades del sistema respiratorio":
                    subItem23.add(causes.intervencion);
                    break;
                case "Enfermedades del sistema digestivo":
                    subItem24.add(causes.intervencion);
                    break;
                case "Enfermedades de la piel":
                    subItem25.add(causes.intervencion);
                    break;
                case "Enfermedades del sistema osteomuscular":
                    subItem26.add(causes.intervencion);
                    break;
                case "Poliarteritis nodosa y afecciones relacionadas":
                    subItem27.add(causes.intervencion);
                    break;
                case "Enfermedades del sistema genitourinario":
                    subItem28.add(causes.intervencion);
                    break;
                case "Ciertas  afecciones originadas  en el periodo perinatal":
                    subItem29.add(causes.intervencion);
                    break;
                case "Malformaciones congénitas, deformidades y anomalías cromosómicas":
                    subItem30.add(causes.intervencion);
                    break;
                case "Síntomas  y signos generales":
                    subItem31.add(causes.intervencion);
                    break;
                case "Traumatismos, envenenamientos y algunas otras consecuencias de causas externas":
                    subItem32.add(causes.intervencion);
                    break;
                case "Quemaduras y corrosiones":
                    subItem33.add(causes.intervencion);
                    break;
                case "Complicaciones de la atención médica y quirúrgica":
                    subItem34.add(causes.intervencion);
                    break;
                case "Factores que influyen  en el estado de salud y contacto  con los servicios de salud":
                    subItem35.add(causes.intervencion);
                    break;
            }
        }

        if(opcion == 1) {
            listDataChildCauses.put(listDataHeaderCauses.get(0), subItem1); // Header, Child data
            listDataChildCauses.put(listDataHeaderCauses.get(1), subItem2);
            listDataChildCauses.put(listDataHeaderCauses.get(2), subItem3);
            listDataChildCauses.put(listDataHeaderCauses.get(3), subItem4);
            listDataChildCauses.put(listDataHeaderCauses.get(4), subItem5);
            listDataChildCauses.put(listDataHeaderCauses.get(5), subItem6); // Header, Child data
        }
        else if(opcion == 2) {
            listDataChildGastosCatastroficos.put(listDataHeaderGastosCatastroficos.get(0), subItem7);
            listDataChildGastosCatastroficos.put(listDataHeaderGastosCatastroficos.get(1), subItem8);
            listDataChildGastosCatastroficos.put(listDataHeaderGastosCatastroficos.get(2), subItem9);
            listDataChildGastosCatastroficos.put(listDataHeaderGastosCatastroficos.get(3), subItem10);
            listDataChildGastosCatastroficos.put(listDataHeaderGastosCatastroficos.get(4), subItem11); // Header, Child data
            listDataChildGastosCatastroficos.put(listDataHeaderGastosCatastroficos.get(5), subItem12);
            listDataChildGastosCatastroficos.put(listDataHeaderGastosCatastroficos.get(6), subItem13);
            listDataChildGastosCatastroficos.put(listDataHeaderGastosCatastroficos.get(7), subItem14);
        }
        else if(opcion == 3) {
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(0), subItem15);
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(1), subItem16); // Header, Child data
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(2), subItem17);
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(3), subItem18);
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(4), subItem19);
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(5), subItem20);
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(6), subItem21); // Header, Child data
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(7), subItem22);
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(8), subItem23);
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(9), subItem24);
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(10), subItem25);
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(11), subItem26); // Header, Child data
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(12), subItem27);
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(13), subItem28);
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(14), subItem29);
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(15), subItem30);
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(16), subItem31); // Header, Child data
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(17), subItem32);
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(18), subItem33);
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(19), subItem34);
            listDataChildSeguroMedico.put(listDataHeaderSeguroMedico.get(20), subItem35);
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.unidades_salud_menu, menu);
        search = menu.findItem(R.id.searchUnidadesSalud);
        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.unidades_salud_menu, menu);
        search = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        //((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setTextColor(Color.WHITE);

        //searchView.setQueryHint("");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                    mAdapter.getFilter().filter(newText);
                if(!newText.isEmpty()){
                    elvCauses.setVisibility(View.GONE);
                    lstCauses.setVisibility(View.VISIBLE);
                }
                else{
                    elvCauses.setVisibility(View.VISIBLE);
                    lstCauses.setVisibility(View.GONE);
                }
                return false;
            }

        });
        return true;
    }
}
