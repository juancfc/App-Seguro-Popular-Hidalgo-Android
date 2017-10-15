package com.repss.apprepss;

/**
 * Propiedades del objeto DependenciaSalud
 */
public class DependenciaSalud {

    int id;
    String Nombre;
    String Clues;
    String Clave;
    String Municipio;
    String Localidad;
    String TipoEstablecimiento;
    String Direccion;
    String CodigoPostal;
    String Telefono;
    String Horario;
    String AccionesSaludPublica;
    String ConsultaMedicinaGeneralFamiliar;
    String Odontologia;
    String Anestesiologia;
    String Cirugia;
    String GinecologiaObstetricia;
    String MedicinaInterna;
    String Pediatra;
    String TraumaOrtopedia;
    String AtencionUrgencias;
    String Radiologia;
    String LaboratorioClinico;
    String BancoSangre;
    String Latitud;
    String Longitud;
    String Gestor;
    String Unidad;

    public DependenciaSalud() {
    }

    public DependenciaSalud(String nombre) {
        this.Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
