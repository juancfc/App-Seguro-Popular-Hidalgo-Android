package com.repss.apprepss;


import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


/**
 * Se muestra un formulario para despues enviarlo por correo y un vículo a la app del telefono para marcar un número designado
 */
public class SolicitudAtencionFragment extends Fragment {

    EditText edtContacto, edtDenuncia;

    public SolicitudAtencionFragment() {
    }

    /**
     * Eventos para la navegación a la app de teléfono pasando el número, también se realiza el envío de la información del formulario a un correo
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_solicitud_atencion, container, false);

        ImageView imvPhone = (ImageView)view.findViewById(R.id.imvPhone);

        Button btnEnviarDenuncia = (Button)view.findViewById(R.id.btnEnviarDenuncia);

        //Solicitud
        edtContacto = (EditText)view.findViewById(R.id.edtContacto);
        edtDenuncia = (EditText)view.findViewById(R.id.edtDenuncia);

        imvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:018007678527")); //
                startActivity(i);
            }
        });

        btnEnviarDenuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtDenuncia.getText().toString().equals("")){
                    SendMail sendMail = null;
                    if (!edtContacto.getText().toString().equals(""))
                        //Toast.makeText(UsuarioActivity.this, "Hubo un error, favor de intentar de nuevo más tarde", Toast.LENGTH_SHORT).show();
                        sendMail = new SendMail(getContext(),"desarrolloprospectiva@gmail.com","Denuncia App REPSS",edtDenuncia.getText().toString() + "\n\n" + "Información de contacto: " + edtContacto.getText().toString(),edtContacto,edtDenuncia);
                    else
                        //Toast.makeText(UsuarioActivity.this, "Error, favor de intentar de nuevo más tarde", Toast.LENGTH_SHORT).show();
                        sendMail = new SendMail(getContext(),"desarrolloprospectiva@gmail.com","Denuncia App REPSS",edtDenuncia.getText().toString(),edtContacto,edtDenuncia);

                    sendMail.execute();

                }else {
                    edtDenuncia.setError("Este campo no puede quedar vacío");
                }

            }
        });

        return view;
    }

}
