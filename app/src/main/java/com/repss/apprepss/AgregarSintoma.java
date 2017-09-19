package com.repss.apprepss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarSintoma extends AppCompatActivity {

    DbHelper helper;
    DataBaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_sintoma);

        final EditText edtHistorialDescripcion = (EditText)findViewById(R.id.edtHistorialDescripcion);
        final Button btnGuardarSintoma = (Button)findViewById(R.id.btnGuardarSintoma);





        btnGuardarSintoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtHistorialDescripcion.getText().toString().isEmpty()) {
                    manager = new DataBaseManager(getApplicationContext());

                    manager.InsertSintomas(edtHistorialDescripcion.getText().toString());

                    Toast.makeText(AgregarSintoma.this, "Se ha guardado con éxito", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(AgregarSintoma.this, ContainerActivity.class).putExtra("opcion", 2));
                }else{
                    edtHistorialDescripcion.setError("Ingrese una descripción");
                }

            }
        });
    }
}
