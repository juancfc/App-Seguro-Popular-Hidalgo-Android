package com.repss.apprepss;

/**
 * Created by maste on 02/09/2016.
 */
public class Sintomas {

    int id;
    String nombre;

    public Sintomas() {
    }

    public Sintomas(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return nombre;
    }

    public void setDescripcion(String nombre) {
        this.nombre = nombre;
    }
}
