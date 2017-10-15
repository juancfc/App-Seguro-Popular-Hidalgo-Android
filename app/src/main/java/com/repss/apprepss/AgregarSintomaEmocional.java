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
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Activity donde se agregan sintomas emocionales
 */
public class AgregarSintomaEmocional extends AppCompatActivity {

    DbHelper helper;
    DataBaseManager manager;

    Button btnFechaEmocional,btnGuardarSintomaEmocional;

    ImageView imvAsustado,imvCansado,imvContento,imvEnojado,imvHambriento,imvTriste;

    Calendar myCalendar = Calendar.getInstance();


    int intensidad;

    String Emocional;

    /**
     * Se crea la vista y se obtiene acceso a ella,  eventos para guardar, seleccionar un image view o para abrir un date picker
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_sintoma_emocional);

        final Bundle bundle = getIntent().getExtras();

        btnFechaEmocional = (Button)findViewById(R.id.btnFechaEmocional);
        btnGuardarSintomaEmocional = (Button)findViewById(R.id.btnGuardarSintomaEmocional);
        imvAsustado = (ImageView)findViewById(R.id.imvAsustado);
        imvCansado = (ImageView)findViewById(R.id.imvCansado);
        imvContento = (ImageView)findViewById(R.id.imvContento);
        imvEnojado = (ImageView)findViewById(R.id.imvEnojado);
        imvHambriento = (ImageView)findViewById(R.id.imvHambriento);
        imvTriste = (ImageView)findViewById(R.id.imvTriste);

        btnFechaEmocional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog().show();
            }
        });

        btnGuardarSintomaEmocional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(intensidad == 0 || intensidad > 6) && !btnFechaEmocional.getText().toString().equals("Selecciona la fecha")) {
                    manager = new DataBaseManager(getApplicationContext());

                    try {
                        manager.InsertSintomasEmocionales(Emocional, btnFechaEmocional.getText().toString(), bundle.getInt("idSintoma"));
                    } catch (Exception ex) {
                        Log.e("TAG", ex.toString());
                    }
                    Toast.makeText(AgregarSintomaEmocional.this, "Se ha guardado con éxito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AgregarSintomaEmocional.this, ListaSintomas.class);
                    Bundle b = new Bundle();
                    try {
                        b.putInt("idSintoma", bundle.getInt("idSintoma"));
                    } catch (Exception ex) {
                        Log.e("TAG", ex.toString());
                    }
                    intent.putExtras(b);
                    startActivity(intent);
                }
                else{
                    if (intensidad == 0 || intensidad > 6){
                        Toast.makeText(AgregarSintomaEmocional.this, "Seleccione su estado emocional", Toast.LENGTH_SHORT).show();
                    }
                    if(btnFechaEmocional.getText().toString().equals("Selecciona la fecha")){
                        Toast.makeText(AgregarSintomaEmocional.this, "Seleccione la fecha", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        imvAsustado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intensidad = 1;
                itemSelected(intensidad);
            }
        });

        imvCansado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intensidad = 2;
                itemSelected(intensidad);
            }
        });

        imvContento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intensidad = 3;
                itemSelected(intensidad);
            }
        });

        imvEnojado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intensidad = 4;
                itemSelected(intensidad);
            }
        });

        imvHambriento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intensidad = 5;
                itemSelected(intensidad);
            }
        });

        imvTriste.setOnClickListener(new View.OnClickListener() {
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

        btnFechaEmocional.setText(sdf.format(myCalendar.getTime()));
    }

    /**
     * Evento del date picker para almacenar en una variable la fecha seleccionada
     */
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
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
                Emocional = "Asustado";
                imvAsustado.setBackgroundColor(getResources().getColor(R.color.azul));
                imvCansado.setBackgroundColor(Color.TRANSPARENT);
                imvContento.setBackgroundColor(Color.TRANSPARENT);
                imvEnojado.setBackgroundColor(Color.TRANSPARENT);
                imvHambriento.setBackgroundColor(Color.TRANSPARENT);
                imvTriste.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 2:
                Emocional = "Cansado";
                imvAsustado.setBackgroundColor(Color.TRANSPARENT);
                imvCansado.setBackgroundColor(getResources().getColor(R.color.azul));
                imvContento.setBackgroundColor(Color.TRANSPARENT);
                imvEnojado.setBackgroundColor(Color.TRANSPARENT);
                imvHambriento.setBackgroundColor(Color.TRANSPARENT);
                imvTriste.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 3:
                Emocional = "Contento";
                imvAsustado.setBackgroundColor(Color.TRANSPARENT);
                imvCansado.setBackgroundColor(Color.TRANSPARENT);
                imvContento.setBackgroundColor(getResources().getColor(R.color.azul));
                imvEnojado.setBackgroundColor(Color.TRANSPARENT);
                imvHambriento.setBackgroundColor(Color.TRANSPARENT);
                imvTriste.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 4:
                Emocional = "Enojado";
                imvAsustado.setBackgroundColor(Color.TRANSPARENT);
                imvCansado.setBackgroundColor(Color.TRANSPARENT);
                imvContento.setBackgroundColor(Color.TRANSPARENT);
                imvEnojado.setBackgroundColor(getResources().getColor(R.color.azul));
                imvHambriento.setBackgroundColor(Color.TRANSPARENT);
                imvTriste.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 5:
                Emocional = "Hambriento";
                imvAsustado.setBackgroundColor(Color.TRANSPARENT);
                imvCansado.setBackgroundColor(Color.TRANSPARENT);
                imvContento.setBackgroundColor(Color.TRANSPARENT);
                imvEnojado.setBackgroundColor(Color.TRANSPARENT);
                imvHambriento.setBackgroundColor(getResources().getColor(R.color.azul));
                imvTriste.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 6:
                Emocional = "Triste";
                imvAsustado.setBackgroundColor(Color.TRANSPARENT);
                imvCansado.setBackgroundColor(Color.TRANSPARENT);
                imvContento.setBackgroundColor(Color.TRANSPARENT);
                imvEnojado.setBackgroundColor(Color.TRANSPARENT);
                imvHambriento.setBackgroundColor(Color.TRANSPARENT);
                imvTriste.setBackgroundColor(getResources().getColor(R.color.azul));
                break;
        }
    }

    /**
     * se crea un date picker para asignar la fecha
     * @return regresa el date picker creado
     */
    private DatePickerDialog datePickerDialog(){
        DatePickerDialog datePicker = new DatePickerDialog(AgregarSintomaEmocional.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        datePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        return datePicker;
    }
}
