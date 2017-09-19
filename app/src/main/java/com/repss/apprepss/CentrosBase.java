package com.repss.apprepss;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by maste on 12/05/2016.
 */
public class CentrosBase {

    DbHelper helper;
    DataBaseManager manager;
    List<CentroAfiliación> lista;
    Cursor cursor;

    public CentrosBase() {
    }

    public void insertCA(Context context) {
        manager = new DataBaseManager(context);
        helper = new DbHelper(context);
        final SQLiteDatabase db = helper.getReadableDatabase();

        //Instruccion para realizar la consulta y almacenarlo en un cursor
        cursor = db.rawQuery(" SELECT * FROM centros_afiliacion;", null);

        if (cursor.getCount() < 1) {
            manager.InsertCA("Coordinación Estatal Pachuca", "Av. Revolucion No. 200 esquina con Daniel Cerecedo , Colonia Periodistas, Pachuca Hgo.",
                    "1530033 Ext. 108", "Angélica Eloísa Mena Islas", "7715679393", "Lunes a Domingo de 8:00 a 19:00 hrs", "maohgo_repss@hotmail.com", "20.12063", "-98.73657");
            manager.InsertCA("Hospital Gral. Pachuca", "Carrt. /Pachuca- Tulancingo #101 A,Col. Cd. De Los Niños",
                    "7134649", "Liliana Rodríguez Rivera", "7711324358", "Lunes a Domingo de 8:00 a 19:00 hrs", "maohgo_pachuca@hotmail.com", "20.11376", "-98.72231");
            manager.InsertCA("Hospital Obstétrico", "AV. Piracantos Esq. Solidaridad",
                    "71-6-61-91, 71- 6-61-92", "Josefina Solís Islas", "tel. 2961307 cel. 7711510286", "Lunes a Viernes de 8:00 a 19:00 hrs y Sabado y Domingo de 8:00 a 20:00 hrs.", "maohgo_obstetrico@hotmail.com", "20.12045", "-98.77426");
            manager.InsertCA("Hospital Niño DIF", "Carretera Mexico Pachuca km 82, Colonia Venta Prieta",
                    "7717179580 ext 145", "Marisol Juárez Rodríguez", "7711633676", " Lunes a Viernes de 8:00 a 16:00 hrs y Sabado y Domingo de 7:00 a 19:00 hrs.", "maohgo_infantil@hotmail.com", "20.07849", "-98.77639");
            manager.InsertCA("Cruz Roja", "Carretera México -Actopan Km 84.38 Fracc. La Herradura",
                    "1700011", "Yozareth Morales Noble", "7712403831", "Lunes a Viernes de 8:00 a 16:00 hrs ", "maohgo_cruzroja@hotmail.com", "20.11393", "-98.72556");
            manager.InsertCA("Centro de salud Jesús del Rosal", "C. Mariano Arista # 707, Col. Nueva Fco. I Madero",
                    "7135912", "Aurora Escamilla Alamilla", "7716837045", "Lunes a Viernes de 7:00 a 16:00 hrs y Sábado y Domingo de 8:00 a 20:00 hrs.", "maohgo_csjrosal@hotmail.com", "20.11876", "-98.7278");
            manager.InsertCA("Centro de salud Nor-Poniente", "Santa Maria Regla, esquina con Circuito Gobernadores 320",
                    "7187837", "María De Jesús Hernández Hernández", "7711107247", "Lunes a Viernes de 8:00 a 16:00 hrs", "maohgo_np_pachuca@hotmail.com", "20.11555", "-98.76859");
            manager.InsertCA("Centro de salud Sur-Poniente", "Blvrd. San Miguel 202 Col. El Tezontle",
                    "7114126", "Rosalina Juarico Tovar", "7711433759", "Lunes a Viernes de 8:00 a 16:00 hrs ", "maohgo_sp_pachuca@hotmail.com", "20.09373", "-98.77757");
            manager.InsertCA("Centro de salud Pachuquilla", "Venustiano Carranza No. 9, Col Centro, Pachuquilla, Mral. De la Reforma, Hgo.",
                    "7717195612", "Francisco Luna Islas", "7711183363", "Lunes y Viernes de 8:00 a 16:00 hrs", "mao_ruta_pachuca@hotmail.com", "20.07126", "-98.69637");
            manager.InsertCA("Centro de salud Epazoyucan", "Av. Hidalgo No. 9, Col. Centro",
                    "s/n", "Francisco Luna Islas", "7711183363", "Martes de 8:00 a 16:00 hrs.", "mao_ruta_pachuca@hotmail.com", "20.01853", "-98.63558");
            manager.InsertCA("Centro de salud Mineral del Monte", "C. Francisco I Madero S/N barrio santa teresa.Mineral del Monte",
                    "s/n", "Francisco Luna Islas", "7711183363", "Jueves de 8:00 a 16:00 hrs.", "mao_ruta_pachuca@hotmail.com", "20.1358", "-98.6747");
            manager.InsertCA("Centro de salud San Agustín Tlaxiaca", "Av. Francisco I Madero S/N San Agustin Tlaxiaca",
                    "s/n", "Francisco Luna Islas", "7711183363", "Miercoles de 8:00 a 16:00 hrs.", "mao_ruta_pachuca@hotmail.com", "20.11513", "-98.88793");
            manager.InsertCA("Hospital General Tulancingo", "C. Lazaro Cardenas 200. Col Centro, Tulancingo",
                    "7757530050", "José Luis Montes De Oca Garcia", "7751389235", "Lunes a Viernes de 8:00 a 18:00 hrs y Sabado y Domingo de 8:00 a 20:00 hrs", "maohgo_tulancingo@hotmail.com", "20.06195", "-98.44088");
            manager.InsertCA("Centro de salud Tulancingo", "Prolongacioon Juarez sn, Col. Caltengo, Tulancingo",
                    "7757531106", "Ailed Erandi Arteaga Villagran", "5543407828", "Lunes a Viernes de 7:00 a 16:00 hrs.", "maohgo_cstulancingo@hotmail.com", "20.09236", "-98.36707");
            manager.InsertCA("Centro de salud Cuautepec", "Av, Hidalgo 71, Col. Centro, Cuautepec de Hinojosa",
                    "7757542660", "Rubén Arturo Ríos Alfaro", "7751066787", "Lunes a Viernes de 8:00 a 16:00 hrs.", "maohgo_cscuautepec@hotmail.com", "20.03777", "-98.30685");
            manager.InsertCA("Centro de salud Santiago Tulantepec", "C. Niños Heroes sn, Col. Vicente Guerrero, Santiago Tulantepec de Lugo Guerrero.",
                    "775751164337", "Lluvia Gabriela Villareal Benítez", "7715698679", "Lunes, Miercoles y Viernes de 8:00 a 16:00 hrs.", "maohgo_santiagosinguilucan@hotmail.com", "20.03534", "-98.35934");
            manager.InsertCA("Centro de salud Singuilucan", "Prol. Guerrero s/n, Fracc. Mayahuelt Singuilucan",
                    "s/n", "Lluvia Gabriela Villareal Benítez", "7715698679", "Martes y Jueves de 8:00 a 16:00 hrs.", "maohgo_santiagosinguilucan@hotmail.com", "19.96726", "-98.52509");
            /*manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
            manager.InsertCA("", "",
                    "", "", "", "", "", "", "");
                    */

        }
    }
}
