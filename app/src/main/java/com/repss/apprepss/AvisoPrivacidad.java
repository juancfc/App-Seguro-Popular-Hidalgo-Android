package com.repss.apprepss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;


public class AvisoPrivacidad extends AppCompatActivity {

    public String pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso_privacidad);

        //nombre del archivo pdf
        pdfView = "AvisoPrivacidad.pdf";

        //Se inicializa el control
        PDFView avisoPrivacidadWebView = (PDFView) findViewById(R.id.avisoPrivacidadPdfView);

        //Se asigna el nombre y lo muestra
        avisoPrivacidadWebView.fromAsset(pdfView)
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                .load();
    }
}
