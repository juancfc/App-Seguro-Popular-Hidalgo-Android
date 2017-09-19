package com.repss.apprepss;

import android.content.SharedPreferences;

/**
 * Created by maste on 30/05/2016.
 */
public class AppSettings {
    public void SaveLoginInfo(SharedPreferences settings,String folio, String consecutivo, String nombres, String apellidoPaterno, String apellidoMaterno, String CURP, String status, String dependenciaSalud,String CLUES, String fechaVencimiento, String sexo, String edad, String tag){
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("userSesion",true);
        editor.putString("userStatus",status);
        editor.putString("userFolio",folio);
        editor.putString("userConsecutivo",consecutivo);
        editor.putString("userNombres",nombres);
        editor.putString("userApellidoPaterno",apellidoPaterno);
        editor.putString("userApellidoMaterno",apellidoMaterno);
        editor.putString("userCURP",CURP);
        editor.putString("userFechaVencimiento",fechaVencimiento);
        editor.putString("userDependenciaSalud",dependenciaSalud);
        editor.putString("userCLUES",CLUES);
        editor.putBoolean("userMensaje",false);
        editor.putString("userSexo",sexo);
        editor.putString("userEdad",edad);
        editor.putString("userTag",tag);
        editor.apply();
    }

    public void CleanSession (SharedPreferences settings){
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("userSesion",false);
        editor.putString("userStatus","");
        editor.putString("userFolio","");
        editor.putString("userConsecutivo","");
        editor.putString("userNombres","");
        editor.putString("userApellidoPaterno","");
        editor.putString("userApellidoMaterno","");
        editor.putString("userCURP","");
        editor.putString("userFechaVencimiento","");
        editor.putString("userDependenciaSalud","");
        editor.putString("userCLUES","");
        editor.putString("path","");
        editor.putString("path","");
        editor.putBoolean("userMensaje",false);
        editor.putString("userSexo","");
        editor.putString("userEdad","");
        editor.putString("userTag","");
        editor.apply();
    }
}
