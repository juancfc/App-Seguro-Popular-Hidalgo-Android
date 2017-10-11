package com.repss.apprepss;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.microsoft.windowsazure.messaging.NotificationHub;

import java.util.ArrayList;

public class MasOpcionesFragment extends Fragment {

    AppSettings objAppSettings = new AppSettings();

    MasOpcionesAdapter opcionesAdapter;

    public MasOpcionesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mas_opciones, container, false);

        ListView masOpcionesListView = (ListView)view.findViewById(R.id.masOpcionesListView);
        ArrayList<String> tableItems = new ArrayList<String>();
        tableItems.add("Quejas, sugerencias o felicitaciones sobre el servicio recibido");
        tableItems.add("Notificaciones");
        tableItems.add("¿Cómo me siento?");
        tableItems.add("Favoritos");
        tableItems.add("Términos y condiciones");
        tableItems.add("Aviso de privacidad");
        tableItems.add("Cerrar sesión");

        ArrayList<Integer> tableIconos = new ArrayList<Integer>();
        tableIconos.add(R.mipmap.ic_quejas);
        tableIconos.add(R.mipmap.ic_notificacion);
        tableIconos.add(R.mipmap.ic_como_me_siento);
        tableIconos.add(R.mipmap.ic_favoritos);
        tableIconos.add(R.mipmap.ic_terminos);
        tableIconos.add(R.mipmap.ic_aviso_privacidad);
        tableIconos.add(R.mipmap.ic_cerrar_sesion);

        opcionesAdapter = new MasOpcionesAdapter(getContext(),tableItems, tableIconos);

        masOpcionesListView.setAdapter(opcionesAdapter);

        masOpcionesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),ContainerActivity.class);
                if(position == 4) {
                    Intent in = new Intent(getContext(),TerminosActivity.class);
                    startActivity(in);
                }
                else if(position == 5){
                    Intent in = new Intent(getContext(),AvisoPrivacidad.class);
                    startActivity(in);
                }
                else if(position == 6){
                    ProgressDialog

                            progressDialog = new ProgressDialog(getContext());
                    progressDialog.setTitle("Cerrando sesión");
                    progressDialog.setMessage("Espere un momento...");
                    progressDialog.show();
                    final SharedPreferences settings = getActivity().getSharedPreferences("UserSettings", Context.MODE_PRIVATE);
                    final NotificationHub hub = new NotificationHub(NotificationSettings.HubName,
                            NotificationSettings.HubListenConnectionString, getContext());
                    final String FCM_token = FirebaseInstanceId.getInstance().getToken();

                    new AsyncTask<Void,Void,Void>(){

                        @Override
                        protected Void doInBackground(Void... params) {
                            try {
                                hub.unregister();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            getActivity().deleteDatabase("repssHidalgo.sqlite");
                            startActivity(new Intent(getContext(),Login.class));
                            objAppSettings.CleanSession(settings);
                        }
                    }.execute();
                }
                else {
                    intent.putExtra("opcion", position);
                    startActivity(intent);
                }
            }
        });

        return view;
    }

}
