package com.repss.apprepss;

/**
 * Propiedades del objeto SintomasEmocionales con sus getters and setters
 */
public class SintomasEmocionales {

    int id;
    String intensidad;
    String fecha;
    int idSintoma;

    public SintomasEmocionales() {
    }

    public SintomasEmocionales(int id, String intensidad, String fecha, int idSintoma) {
        this.id = id;
        this.intensidad = intensidad;
        this.fecha = fecha;
        this.idSintoma = idSintoma;
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
