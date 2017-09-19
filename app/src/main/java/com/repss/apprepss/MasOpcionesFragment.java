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

public class MasOpcionesFragment extends Fragment {

    AppSettings objAppSettings = new AppSettings();

    public MasOpcionesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mas_opciones, container, false);

        ListView masOpcionesListView = (ListView)view.findViewById(R.id.masOpcionesListView);
        String[] tableItems = new String[] {
                "Quejas, sugerencias o felicitaciones sobre el servicio recibido",
                "Notificaciones",
                "¿Cómo me siento?",
                "Favoritos",
                "Términos y condiciones",
                "Aviso de privacidad",
                "Cerrar sesión"
        };

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, tableItems);

        masOpcionesListView.setAdapter(itemsAdapter);

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
