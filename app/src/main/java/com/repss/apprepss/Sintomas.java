package com.repss.apprepss;

/**
 * Propiedades del objeto Sintomas
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
