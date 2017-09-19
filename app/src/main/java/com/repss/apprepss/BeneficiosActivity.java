package com.repss.apprepss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BeneficiosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficios);

        Button causesButton = (Button) findViewById(R.id.causesButton);
        Button gastosCatastroficosButton = (Button) findViewById(R.id.gastosCatastroficosButton);
        Button seguroMedicoButton = (Button) findViewById(R.id.seguroMedicoButton);

        final Intent listaBeneficiosActivity = new Intent(BeneficiosActivity.this,CausesActivity.class);

        causesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaBeneficiosActivity.putExtra("beneficios",1);
                startActivity(listaBeneficiosActivity);
            }
        });

        gastosCatastroficosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaBeneficiosActivity.putExtra("beneficios",2);
                startActivity(listaBeneficiosActivity);
            }
        });

        seguroMedicoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaBeneficiosActivity.putExtra("beneficios",3);
                startActivity(listaBeneficiosActivity);
            }
        });
    }
}
