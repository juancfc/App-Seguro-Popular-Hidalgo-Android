package com.repss.apprepss;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by maste on 12/05/2016.
 */
public class DataBaseManager {
    private static final String TABLE_NAME_CS = "dependencias_salud";
    private static final String TABLE_NAME_CA = "centros_afiliacion";
    private static final String TABLE_NAME_SINTOMAS = "sintomas";
    private static final String TABLE_NAME_SINTOMA_FISICO = "sintoma_fisico";
    private static final String TABLE_NAME_SINTOMA_EMOCIONAL = "sintoma_emocional";
    private static final String TABLE_NAME_NOTIFICACIONES = "notificaciones";
    private static final String TABLE_NAME_FAVORITOS = "favoritos";


    private static final String CN_CS_ID = "_id";
    private static final String CN_CS_NOMBRE = "nombre";
    private static final String CN_CS_CLUES = "clues";
    private static final String CN_CS_CLAVE = "clave";
    private static final String CN_CS_MUNICIPIO = "municipio";
    private static final String CN_CS_LOCALIDAD = "localidad";
    private static final String CN_CS_TIPO_ESTABLECIMIENTO = "tipo_establecimiento";
    private static final String CN_CS_DIRECCION = "direccion";
    private static final String CN_CS_CODIGO_POSTAL = "codigo_postal";
    private static final String CN_CS_TELEFONO = "telefono";
    private static final String CN_CS_HORARIO = "horario";
    private static final String CN_CS_SERVICIO_1 = "acciones_salud_publica";
    private static final String CN_CS_SERVICIO_2 = "consulta_medicina_general_familiar";
    private static final String CN_CS_SERVICIO_3 = "odontologia";
    private static final String CN_CS_SERVICIO_4 = "anestesiologia";
    private static final String CN_CS_SERVICIO_5 = "cirugia";
    private static final String CN_CS_SERVICIO_6 = "ginecologia_obstetricia";
    private static final String CN_CS_SERVICIO_7 = "medicina_interna";
    private static final String CN_CS_SERVICIO_8 = "pediatria";
    private static final String CN_CS_SERVICIO_9 = "trauma_ortopedia";
    private static final String CN_CS_SERVICIO_10 = "atencion_urgencias";
    private static final String CN_CS_SERVICIO_11 = "radiologia";
    private static final String CN_CS_SERVICIO_12 = "laboratorio_clinico";
    private static final String CN_CS_SERVICIO_13 = "banco_sangre";
    private static final String CN_CS_LATITUD = "latitud";
    private static final String CN_CS_LONGITUD = "longitud";
    private static final String CN_CS_GESTOR = "gestor";
    private static final String CN_CS_UNIDAD = "unidad";
    private static final String CN_CS_DIRECCION_GESTOR = "direccion_gestor";


    private static final String CN_CA_ID = "_id";
    private static final String CN_CA_NOMBRE = "nombre";
    private static final String CN_CA_DIRECCION = "direccion";
    private static final String CN_CA_TELEFONO = "telefono";
    private static final String CN_CA_RESPONSABLE = "responsable";
    private static final String CN_CA_TELEFONORESPONSABLE = "telefono_responsable";
    private static final String CN_CA_HORARIO = "horario";
    private static final String CN_CA_CORREO = "correo";
    private static final String CN_CA_LATITUD = "latitud";
    private static final String CN_CA_LONGITUD = "longitud";

    private static  final String CN_SINTOMA_ID = "_id";
    private static  final String CN_SINTOMA_NOMBRE = "nombre";

    private static  final String CN_FISICO_ID = "_id";
    private static  final String CN_FISICO_DESCRIPCION = "descripcion";
    private static  final String CN_FISICO_INTENSIDAD = "intensidad";
    private static  final String CN_FISICO_FECHA = "fecha";
    private static  final String CN_FISICO_SINTOMA_ID = "_id_sintoma";

    private static  final String CN_EMOCIONAL_ID = "_id";
    private static  final String CN_EMOCIONAL_TIPO = "intensidad";
    private static  final String CN_EMOCIONAL_FECHA = "fecha";
    private static  final String CN_EMOCIONAL_SINTOMA_ID = "_id_sintoma";

    private static  final String CN_NOTIFICACIONES_ID = "_id";
    private static  final String CN_NOTIFICACIONES_TITULO = "titulo";
    private static  final String CN_NOTIFICACIONES_DESCRIPCION = "descripcion";
    private static  final String CN_NOTIFICACIONES_POLIZA = "poliza";

    private static  final String CN_FAVORITOS_ID = "_id";
    private static  final String CN_FAVORITOS_UNIDAD_ID = "_id_unidad";

    public static final String CREATE_TABLE_CS = "create table " + TABLE_NAME_CS + " ("
            + CN_CS_ID + " integer primary key autoincrement,"
            + CN_CS_NOMBRE + " text,"
            + CN_CS_CLUES + " text,"
            + CN_CS_CLAVE + " text,"
            + CN_CS_MUNICIPIO + " text,"
            + CN_CS_LOCALIDAD + " text,"
            + CN_CS_TIPO_ESTABLECIMIENTO + " text,"
            + CN_CS_DIRECCION + " text,"
            + CN_CS_CODIGO_POSTAL + " text,"
            + CN_CS_TELEFONO + " text,"
            + CN_CS_HORARIO + " text,"
            + CN_CS_SERVICIO_1 + " text,"
            + CN_CS_SERVICIO_2 + " text,"
            + CN_CS_SERVICIO_3 + " text,"
            + CN_CS_SERVICIO_4 + " text,"
            + CN_CS_SERVICIO_5 + " text,"
            + CN_CS_SERVICIO_6 + " text,"
            + CN_CS_SERVICIO_7 + " text,"
            + CN_CS_SERVICIO_8 + " text,"
            + CN_CS_SERVICIO_9 + " text,"
            + CN_CS_SERVICIO_10 + " text,"
            + CN_CS_SERVICIO_11 + " text,"
            + CN_CS_SERVICIO_12 + " text,"
            + CN_CS_SERVICIO_13 + " text,"
            + CN_CS_LATITUD + " text,"
            + CN_CS_GESTOR + " text,"
            + CN_CS_UNIDAD + " text,"
            + CN_CS_LONGITUD + " text);";

    public static final String CREATE_TABLE_CA = "create table " + TABLE_NAME_CA + " ("
            + CN_CA_ID + " integer primary key autoincrement,"
            + CN_CA_NOMBRE + " text,"
            + CN_CA_DIRECCION + " text,"
            + CN_CA_TELEFONO + " text,"
            + CN_CA_RESPONSABLE + " text,"
            + CN_CA_TELEFONORESPONSABLE + " text,"
            + CN_CA_HORARIO + " text,"
            + CN_CA_CORREO + " text,"
            + CN_CA_LATITUD + " text,"
            + CN_CA_LONGITUD + " text);";

    public static final String CREATE_TABLE_SINTOMAS = "create table " + TABLE_NAME_SINTOMAS + " ("
            + CN_SINTOMA_ID + " integer primary key autoincrement,"
            + CN_SINTOMA_NOMBRE + " text);";

    public static final String CREATE_TABLE_FISICO = "create table " + TABLE_NAME_SINTOMA_FISICO + " ("
            + CN_FISICO_ID + " integer primary key autoincrement,"
            + CN_FISICO_DESCRIPCION + " text,"
            + CN_FISICO_INTENSIDAD + " text,"
            + CN_FISICO_FECHA + " text,"
            + CN_FISICO_SINTOMA_ID + " integer);";

    public static final String CREATE_TABLE_EMOCIONAL = "create table " + TABLE_NAME_SINTOMA_EMOCIONAL + " ("
            + CN_EMOCIONAL_ID + " integer primary key autoincrement,"
            + CN_EMOCIONAL_TIPO + " text,"
            + CN_EMOCIONAL_FECHA + " text,"
            + CN_EMOCIONAL_SINTOMA_ID + " integer);";

    public static final String CREATE_TABLE_NOTIFICACIONES = "create table " + TABLE_NAME_NOTIFICACIONES + " ("
            + CN_NOTIFICACIONES_ID + " integer primary key autoincrement,"
            + CN_NOTIFICACIONES_TITULO + " text,"
            + CN_NOTIFICACIONES_DESCRIPCION + " text,"
            + CN_NOTIFICACIONES_POLIZA + " text);";

    public static final String CREATE_TABLE_FAVORITOS = "create table " + TABLE_NAME_FAVORITOS + " ("
            + CN_FAVORITOS_ID + " integer primary key autoincrement,"
            + CN_FAVORITOS_UNIDAD_ID + " integer);";

    private DbHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    public ContentValues GenerateContentValuesCS(String nombre, String clues, String clave, String municipio, String localidad,
                                                 String tipoEstablecimiento, String direccion,
                                                 String codigoPostal, String telefono,String horario, String servicio1, String servicio2,
                                                 String servicio3, String servicio4, String servicio5, String servicio6, String servicio7,
                                                 String servicio8, String servicio9, String servicio10, String servicio11,
                                                 String servicio12, String servicio13, String latitud, String longitud, String gestor, String unidad)
    {
        ContentValues values = new ContentValues();
        values.put(CN_CS_NOMBRE,nombre);
        values.put(CN_CS_CLUES,clues);
        values.put(CN_CS_CLAVE,clave);
        values.put(CN_CS_MUNICIPIO,municipio);
        values.put(CN_CS_LOCALIDAD,localidad);
        values.put(CN_CS_TIPO_ESTABLECIMIENTO,tipoEstablecimiento);
        values.put(CN_CS_DIRECCION,direccion);
        values.put(CN_CS_CODIGO_POSTAL,codigoPostal);
        values.put(CN_CS_TELEFONO,telefono);
        values.put(CN_CS_HORARIO,horario);
        values.put(CN_CS_SERVICIO_1,servicio1);
        values.put(CN_CS_SERVICIO_2,servicio2);
        values.put(CN_CS_SERVICIO_3,servicio3);
        values.put(CN_CS_SERVICIO_4,servicio4);
        values.put(CN_CS_SERVICIO_5,servicio5);
        values.put(CN_CS_SERVICIO_6,servicio6);
        values.put(CN_CS_SERVICIO_7,servicio7);
        values.put(CN_CS_SERVICIO_8,servicio8);
        values.put(CN_CS_SERVICIO_9,servicio9);
        values.put(CN_CS_SERVICIO_10,servicio10);
        values.put(CN_CS_SERVICIO_11,servicio11);
        values.put(CN_CS_SERVICIO_12,servicio12);
        values.put(CN_CS_SERVICIO_13,servicio13);
        values.put(CN_CS_LATITUD,latitud);
        values.put(CN_CS_LONGITUD,longitud);
        values.put(CN_CS_GESTOR,gestor);
        values.put(CN_CS_UNIDAD,unidad);

        return values;
    }

    public ContentValues GenerateContentValuesCA(String nombre, String direccion,String telefono, String responsable, String telefonoResponsable,String horario,String correo,String latitud, String longitud)
    {
        ContentValues values = new ContentValues();
        values.put(CN_CA_NOMBRE,nombre);
        values.put(CN_CA_DIRECCION,direccion);
        values.put(CN_CA_TELEFONO,telefono);
        values.put(CN_CA_RESPONSABLE,responsable);
        values.put(CN_CA_TELEFONORESPONSABLE, telefonoResponsable);
        values.put(CN_CA_HORARIO,horario);
        values.put(CN_CA_CORREO,correo);
        values.put(CN_CA_LATITUD,latitud);
        values.put(CN_CA_LONGITUD,longitud);

        return values;
    }

    public ContentValues GenerateContentValuesSintomas(String nombre)
    {
        ContentValues values = new ContentValues();
        values.put(CN_SINTOMA_NOMBRE,nombre);

        return values;
    }

    public ContentValues GenerateContentValuesSintomasFisicos(String descripcion, String intensidad, String fecha, int sintomaId)
    {
        ContentValues values = new ContentValues();
        values.put(CN_FISICO_DESCRIPCION,descripcion);
        values.put(CN_FISICO_INTENSIDAD,intensidad);
        values.put(CN_FISICO_FECHA,fecha);
        values.put(CN_FISICO_SINTOMA_ID,sintomaId);

        return values;
    }

    public ContentValues GenerateContentValuesSintomasEmocionales(String tipo, String fecha, int sintomaId)
    {
        ContentValues values = new ContentValues();
        values.put(CN_EMOCIONAL_TIPO,tipo);
        values.put(CN_EMOCIONAL_FECHA,fecha);
        values.put(CN_EMOCIONAL_SINTOMA_ID,sintomaId);

        return values;
    }

    public ContentValues GenerateContentValuesNotificaciones(String titulo, String descripcion,String poliza)
    {
        ContentValues values = new ContentValues();
        values.put(CN_NOTIFICACIONES_TITULO,titulo);
        values.put(CN_NOTIFICACIONES_DESCRIPCION,descripcion);
        values.put(CN_NOTIFICACIONES_POLIZA,poliza);

        return values;
    }

    public ContentValues GenerateContentValuesFavoritos(int unidadId)
    {
        ContentValues values = new ContentValues();
        values.put(CN_FAVORITOS_UNIDAD_ID,unidadId);

        return values;
    }

    public void InsertCS(String nombre, String clues, String clave, String municipio, String localidad,
                         String tipoEstablecimiento, String direccion,
                         String codigoPostal, String telefono,String horario, String servicio1, String servicio2,
                         String servicio3, String servicio4, String servicio5, String servicio6, String servicio7,
                         String servicio8, String servicio9, String servicio10, String servicio11,
                         String servicio12, String servicio13, String latitud, String longitud, String gestor, String unidad)
    {
        db.insert(TABLE_NAME_CS, null, GenerateContentValuesCS(nombre, clues, clave, municipio, localidad,
                tipoEstablecimiento, direccion,
                codigoPostal, telefono, horario, servicio1, servicio2,
                servicio3, servicio4, servicio5, servicio6, servicio7,
                servicio8, servicio9,  servicio10,  servicio11,
                servicio12, servicio13, latitud,  longitud, gestor,unidad));
    }

    public void InsertCA(String nombre, String direccion,String telefono, String responsable, String telefonoResponsable,String horario,String correo,String latitud,String longitud)
    {
        db.insert(TABLE_NAME_CA, null, GenerateContentValuesCA(nombre, direccion, telefono, responsable, telefonoResponsable,horario,correo,latitud,longitud));
    }

    public void InsertSintomas(String nombre)
    {
        db.insert(TABLE_NAME_SINTOMAS, null, GenerateContentValuesSintomas(nombre));
    }

    public void InsertSintomasFisicos(String descripcion,String intensidad, String fecha, int sintomaId)
    {
        db.insert(TABLE_NAME_SINTOMA_FISICO, null, GenerateContentValuesSintomasFisicos(descripcion, intensidad, fecha, sintomaId));
    }

    public void InsertSintomasEmocionales(String tipo, String fecha, int sintomaId)
    {
        db.insert(TABLE_NAME_SINTOMA_EMOCIONAL, null, GenerateContentValuesSintomasEmocionales(tipo, fecha, sintomaId));
    }

    public void InsertNotificaciones(String titulo,String descripcion, String poliza)
    {
        db.insert(TABLE_NAME_NOTIFICACIONES, null, GenerateContentValuesNotificaciones(titulo, descripcion, poliza));
    }

    public void InsertFavoritos(int unidadId)
    {
        db.insert(TABLE_NAME_FAVORITOS, null, GenerateContentValuesFavoritos(unidadId));
    }

}
