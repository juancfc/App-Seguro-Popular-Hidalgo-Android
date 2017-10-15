package com.repss.apprepss;

/**
 * Propiedades del objeto CentroAfiliacion
 */

public class CentroAfiliacion {

    int id;
    String Nombre;
    String Direccion;
    String Telefono;
    String Responsable;
    String TelefonoResponsable;
    String Horario;
    String Correo;
    String Latitud;
    String Longitud;

    public CentroAfiliacion() {
    }

    public CentroAfiliacion(String nombre) {
        Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
