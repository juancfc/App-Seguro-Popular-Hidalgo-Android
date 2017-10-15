package com.repss.apprepss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

/**
 * Activity que muestra los terminos y condiciones de la app
 */
public class TerminosActivity extends AppCompatActivity {

    public String pdfView;

    /**
     * Se carga el pdf en el control referenciado
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminos);

        //Nombre del pdf
        pdfView = "TerminosyCondiciones.pdf";

        PDFView terminosWebView = (PDFView) findViewById(R.id.terminosPdfView);

        //Se carga el archivo al control
        terminosWebView.fromAsset(pdfView)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true)
                .load();
    }
}
