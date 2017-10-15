package com.repss.apprepss;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Se crea la conexión con la base de datos lodal
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "repssHidalgo.sqlite";
    private static final int DB_SCHEME_VERSION = 1;


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    /**
     * Se crean las tablas en caso de que no existan
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DataBaseManager.CREATE_TABLE_CS);
        db.execSQL(DataBaseManager.CREATE_TABLE_CA);
        db.execSQL(DataBaseManager.CREATE_TABLE_SINTOMAS);
        db.execSQL(DataBaseManager.CREATE_TABLE_FISICO);
        db.execSQL(DataBaseManager.CREATE_TABLE_EMOCIONAL);
        db.execSQL(DataBaseManager.CREATE_TABLE_NOTIFICACIONES);
        db.execSQL(DataBaseManager.CREATE_TABLE_FAVORITOS);
    }


    /**
     * Se actualiza la bd
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
