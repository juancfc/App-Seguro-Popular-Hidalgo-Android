package com.repss.apprepss;

/**
 * Propiedades del objeto SintomasFisicos con sus getters and setters
 */
public class SintomasFisicos {
    int id;
    String descripcion;
    String intensidad;
    String fecha;
    int idSintoma;

    public SintomasFisicos() {
    }

    public SintomasFisicos(int id, String descripcion, String intensidad, String fecha, int idSintoma) {
        this.id = id;
        this.descripcion = descripcion;
        this.intensidad = intensidad;
        this.fecha = fecha;
        this.idSintoma = idSintoma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(String intensidad) {
        this.intensidad = intensidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdSintoma() {
        return idSintoma;
    }

    public void setIdSintoma(int idSintoma) {
        this.idSintoma = idSintoma;
    }
}
