package com.repss.apprepss;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Activity donde se agregan sintomas fisicos
 */
public class AgregarSintomaFisico extends AppCompatActivity {

    DbHelper helper;
    DataBaseManager manager;

    Button btnFecha,btnGuardarSintomaFisico;

    ImageView imvSinDolor,imvDolorLeve,imvDolorModerado,imvDolorSevero,imvDolorMuySevero,imvMaximoDolor;

    Calendar myCalendar = Calendar.getInstance();

    EditText edtDescripcionFisico;

    int intensidad;

    String Dolor;

    /**
     * Se crea la vista y se obtiene acceso a ella,  eventos para guardar, seleccionar un image view o para abrir un date picker
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_sintoma_fisico);

        final Bundle bundle = getIntent().getExtras();

        edtDescripcionFisico = (EditText)findViewById(R.id.edtDescripcionFisico);
        btnFecha = (Button)findViewById(R.id.btnFecha);
        btnGuardarSintomaFisico = (Button)findViewById(R.id.btnGuardarSintomaFisico);
        imvSinDolor = (ImageView)findViewById(R.id.imvSinDolor);
        imvDolorLeve = (ImageView)findViewById(R.id.imvDolorLeve);
        imvDolorModerado = (ImageView)findViewById(R.id.imvDolorModerado);
        imvDolorSevero = (ImageView)findViewById(R.id.imvDolorSevero);
        imvDolorMuySevero = (ImageView)findViewById(R.id.imvDolorMuySevero);
        imvMaximoDolor = (ImageView)findViewById(R.id.imvMaximoDolor);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog().show();
            }
        });

        btnGuardarSintomaFisico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(intensidad == 0 || intensidad > 6) && !btnFecha.getText().toString().equals("Selecciona la fecha")&&!edtDescripcionFisico.getText().toString().isEmpty()) {
                    manager = new DataBaseManager(getApplicationContext());

                    try {
                        manager.InsertSintomasFisicos(edtDescripcionFisico.getText().toString(), Dolor, btnFecha.getText().toString(), bundle.getInt("idSintoma"));
                    } catch (Exception ex) {
                        Log.e("TAG", ex.toString());
                    }
                    Toast.makeText(AgregarSintomaFisico.this, "Se ha guardado con éxito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AgregarSintomaFisico.this, ListaSintomas.class);
                    Bundle b = new Bundle();
                    try {
                        b.putInt("idSintoma", bundle.getInt("idSintoma"));
                    } catch (Exception ex) {
                        Log.e("TAG", ex.toString());
                    }
                    intent.putExtras(b);
                    startActivity(intent);
                }
                else
                {
                    if (intensidad == 0 || intensidad > 6){
                        Toast.makeText(AgregarSintomaFisico.this, "Seleccione su estado emocional", Toast.LENGTH_SHORT).show();
                    }
                    if(btnFecha.getText().toString().equals("Selecciona la fecha")){
                        Toast.makeText(AgregarSintomaFisico.this, "Seleccione la fecha", Toast.LENGTH_SHORT).show();
                    }
                    if(edtDescripcionFisico.getText().toString().isEmpty()){
                        edtDescripcionFisico.setError("Agrega una descripción");
                    }
                }
            }
        });

        imvSinDolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intensidad = 1;
                itemSelected(intensidad);
            }
        });

        imvDolorLeve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intensidad = 2;
                itemSelected(intensidad);
            }
        });

        imvDolorModerado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intensidad = 3;
                itemSelected(intensidad);
            }
        });

        imvDolorSevero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intensidad = 4;
                itemSelected(intensidad);
            }
        });

        imvDolorMuySevero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intensidad = 5;
                itemSelected(intensidad);
            }
        });

        imvMaximoDolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intensidad = 6;
                itemSelected(intensidad);
            }
        });
    }

    /**
     * Se actualiza la etiqueta del button con la fecha seleccionada en el date picker
     */
    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        btnFecha.setText(sdf.format(myCalendar.getTime()));
    }

    /**
     * Evento del date picker para almacenar en una variable la fecha seleccionada
     */
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    /**
     * se muestra la seleccion de un image view
     * @param item item seleccionado de la lista de image view
     */
    private void itemSelected(int item){
        switch (item){
            case 1:
                Dolor = "Sin Dolor";
                imvSinDolor.setBackgroundColor(getResources().getColor(R.color.azul));
                imvDolorLeve.setBackgroundColor(Color.TRANSPARENT);
                imvDolorModerado.setBackgroundColor(Color.TRANSPARENT);
                imvDolorSevero.setBackgroundColor(Color.TRANSPARENT);
                imvDolorMuySevero.setBackgroundColor(Color.TRANSPARENT);
                imvMaximoDolor.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 2:
                Dolor = "Dolor Leve";
                imvSinDolor.setBackgroundColor(Color.TRANSPARENT);
                imvDolorLeve.setBackgroundColor(getResources().getColor(R.color.azul));
                imvDolorModerado.setBackgroundColor(Color.TRANSPARENT);
                imvDolorSevero.setBackgroundColor(Color.TRANSPARENT);
                imvDolorMuySevero.setBackgroundColor(Color.TRANSPARENT);
                imvMaximoDolor.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 3:
                Dolor = "Dolor Moderado";
                imvSinDolor.setBackgroundColor(Color.TRANSPARENT);
                imvDolorLeve.setBackgroundColor(Color.TRANSPARENT);
                imvDolorModerado.setBackgroundColor(getResources().getColor(R.color.azul));
                imvDolorSevero.setBackgroundColor(Color.TRANSPARENT);
                imvDolorMuySevero.setBackgroundColor(Color.TRANSPARENT);
                imvMaximoDolor.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 4:
                Dolor = "Dolor Severo";
                imvSinDolor.setBackgroundColor(Color.TRANSPARENT);
                imvDolorLeve.setBackgroundColor(Color.TRANSPARENT);
                imvDolorModerado.setBackgroundColor(Color.TRANSPARENT);
                imvDolorSevero.setBackgroundColor(getResources().getColor(R.color.azul));
                imvDolorMuySevero.setBackgroundColor(Color.TRANSPARENT);
                imvMaximoDolor.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 5:
                Dolor = "Dolor Muy Severo";
                imvSinDolor.setBackgroundColor(Color.TRANSPARENT);
                imvDolorLeve.setBackgroundColor(Color.TRANSPARENT);
                imvDolorModerado.setBackgroundColor(Color.TRANSPARENT);
                imvDolorSevero.setBackgroundColor(Color.TRANSPARENT);
                imvDolorMuySevero.setBackgroundColor(getResources().getColor(R.color.azul));
                imvMaximoDolor.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 6:
                Dolor = "Máximo Dolor";
                imvSinDolor.setBackgroundColor(Color.TRANSPARENT);
                imvDolorLeve.setBackgroundColor(Color.TRANSPARENT);
                imvDolorModerado.setBackgroundColor(Color.TRANSPARENT);
                imvDolorSevero.setBackgroundColor(Color.TRANSPARENT);
                imvDolorMuySevero.setBackgroundColor(Color.TRANSPARENT);
                imvMaximoDolor.setBackgroundColor(getResources().getColor(R.color.azul));
                break;
        }
    }

    /**
     * se crea un date picker para asignar la fecha
     * @return regresa el date picker creado
     */
    private DatePickerDialog datePickerDialog(){
        DatePickerDialog datePicker = new DatePickerDialog(AgregarSintomaFisico.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        datePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        return datePicker;
    }

}
