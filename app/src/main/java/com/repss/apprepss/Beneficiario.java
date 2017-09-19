package com.repss.apprepss;

import java.util.Date;

/**
 * Created by maste on 27/05/2016.
 */
public class Beneficiario {

    static Date fechaVencimiento;
    static String status;
    static String folio;
    static String consecutivo;
    static String nombres;
    static String apellidoPaterno;
    static String apellidoMaterno;
    static String CURP;
    static String dependenciaSalud;
    static String CLUES;
    static String sexo;
    static String edad;

    public static String getFolio() {
        return folio;
    }

    public static void setFolio(String folio) {
        Beneficiario.folio = folio;
    }

    public static String getConsecutivo() {
        return consecutivo;
    }

    public static void setConsecutivo(String consecutivo) {
        Beneficiario.consecutivo = consecutivo;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static String getNombres() {
        return nombres;
    }

    public static void setNombres(String nombres) {
        Beneficiario.nombres = nombres;
    }

    public static String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public static void setApellidoPaterno(String apellidoPaterno) {
        Beneficiario.apellidoPaterno = apellidoPaterno;
    }

    public static String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public static void setApellidoMaterno(String apellidoMaterno) {
        Beneficiario.apellidoMaterno = apellidoMaterno;
    }

    public static String getCURP() {
        return CURP;
    }

    public static void setCURP(String CURP) {
        Beneficiario.CURP = CURP;
    }

    public static String getDependenciaSalud() {
        return dependenciaSalud;
    }

    public static void setDependenciaSalud(String dependenciaSalud) {
        Beneficiario.dependenciaSalud = dependenciaSalud;
    }

    public static String getCLUES() {
        return CLUES;
    }

    public static void setCLUES(String CLUES) {
        Beneficiario.CLUES = CLUES;
    }

    public static String getSexo() {
        return sexo;
    }

    public static void setSexo(String sexo) {
        Beneficiario.sexo = sexo;
    }

    public static String getEdad() {
        return edad;
    }

    public static void setEdad(String edad) {
        Beneficiario.edad = edad;
    }
}
