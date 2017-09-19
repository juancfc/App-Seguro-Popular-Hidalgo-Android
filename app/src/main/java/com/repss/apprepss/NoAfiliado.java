package com.repss.apprepss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NoAfiliado extends AppCompatActivity {

    Button requisitosButton, centrosAfiliacionButton, causesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_afiliado);

        requisitosButton = (Button)findViewById(R.id.requisitosButton);
        centrosAfiliacionButton = (Button)findViewById(R.id.centrosAfiliacionButton);
        causesButton = (Button)findViewById(R.id.causesButton);

        requisitosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoAfiliado.this, RequisitosActivity.class));
            }
        });

        centrosAfiliacionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( NoAfiliado.this, MAOS.class ));
            }
        });

        causesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoAfiliado.this, BeneficiosActivity.class));
            }
        });
    }
}
