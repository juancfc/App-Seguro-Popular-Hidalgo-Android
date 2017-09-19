package com.repss.apprepss;

/**
 * Created by maste on 18/11/2016.
 */
public class Notificacion {

    public int id;
    public String titulo;
    public String descripcion;
    public String poliza;

    public Notificacion() {
    }

    public Notificacion(int id, String titulo, String descripcion, String poliza) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.poliza = poliza;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPoliza() {
        return poliza;
    }

    public void setPoliza(String poliza) {
        this.poliza = poliza;
    }
}
