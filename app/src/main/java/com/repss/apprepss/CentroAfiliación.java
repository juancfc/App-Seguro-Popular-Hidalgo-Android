package com.repss.apprepss;

/**
 * Created by maste on 12/05/2016.
 */
public class CentroAfiliación {
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

    public CentroAfiliación() {
    }

    public CentroAfiliación(String nombre) {
        Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
