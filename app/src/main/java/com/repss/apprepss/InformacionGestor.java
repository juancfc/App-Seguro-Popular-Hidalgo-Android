package com.repss.apprepss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Muestra la información del gestor a cargo de la unidad de salud
 */
public class InformacionGestor extends AppCompatActivity {

    ImageView gestorImageView,segundoGestorImageView;
    TextView nombreGestorTextView, jurisdiccionGestorTextView, turnoGestorTextView, telefonoGestorTextView, correoGestorTextView, direccionGestorTextView, nombreSegundoGestorTextView, turnoSegundoGestorTextView, telefonoSegundoGestorTextView, correoSegundoGestorTextView;
    LinearLayout segundoGestorLinearLayout;

    String gestor, unidad;

    /**
     * Se muestra la foto y la informacion del gestor
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_gestor);

        segundoGestorLinearLayout = (LinearLayout)findViewById(R.id.segundoGestorLinearLayout);

        gestorImageView = (ImageView)findViewById(R.id.gestorImageView);
        nombreGestorTextView = (TextView)findViewById(R.id.nombreGestorTextView);
        turnoGestorTextView = (TextView)findViewById(R.id.turnoGestorTextView);
        telefonoGestorTextView = (TextView)findViewById(R.id.telefonoGestorTextView);
        correoGestorTextView = (TextView)findViewById(R.id.correoGestorTextView);

        segundoGestorImageView = (ImageView)findViewById(R.id.segundoGestorImageView);
        nombreSegundoGestorTextView = (TextView)findViewById(R.id.nombreSegundoGestorTextView);
        turnoSegundoGestorTextView = (TextView)findViewById(R.id.turnoSegundoGestorTextView);
        telefonoSegundoGestorTextView = (TextView)findViewById(R.id.telefonoSegundoGestorTextView);
        correoSegundoGestorTextView = (TextView)findViewById(R.id.correoSegundoGestorTextView);

        jurisdiccionGestorTextView = (TextView)findViewById(R.id.jurisdiccionGestorTextView);
        direccionGestorTextView = (TextView)findViewById(R.id.direccionGestorTextView);

        gestor = getIntent().getStringExtra("gestor");
        unidad = getIntent().getStringExtra("unidad");

        nombreGestorTextView.setText(gestor);
        jurisdiccionGestorTextView.setText(unidad);

        switch (unidad)
        {
            case "Jurisdicción No. I Pachuca":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.carlos_valentin));
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("771 713 54 00 ó 771 719 14 79");
                correoGestorTextView.setText("carlos_gio@hotmail.com");
                direccionGestorTextView.setText("Narciso Dolares #301 Esq. Heroes de Nacozari, Col. Morelos, Pachuca.");
                break;
            }
            case "Jurisdicción No. II Tulancingo":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.fabiola_amrtinez));
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("775 753 22 76");
                correoGestorTextView.setText("fabiolamfregozo@hotmail.com");
                direccionGestorTextView.setText("CALLE LÁZARO CÁRDENAS # 200, COL. CENTRO, MUNICIPIO TULANCINGO DE BRAVO, C. P.43600");
                break;
            }
            case "Jurisdicción No. III Tula":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.qlberto_becerril_garcia));
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("773 73 204 77 ó 773 73 206 50");
                correoGestorTextView.setText("alberto-becerril@live.com.mx");
                direccionGestorTextView.setText("Calle 5 de febrero # 5, Col. Centro, Municipio Tula de Allende, Hidalgo, C. P. 42800");
                break;
            }
            case "Jurisdicción No. IV Huichapan":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.concepcio_gomez_moreo));
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("761 782 16 35 ó 761 782 00 51");
                correoGestorTextView.setText("gm.cony@hotmail.com");
                direccionGestorTextView.setText("Carretera Huichapan -Ixmiquilpan s/n Centro C. P. 42400 HUICHAPAN Hidalgo");
                break;
            }
            case "Jurisdicción No. V Zimapan":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.sandra_godinez));
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("759 728 21 83");
                direccionGestorTextView.setText("Calle Morelos # 13 col. Centro, Zimapan Hidalgo.");
                correoGestorTextView.setText("godinezchavez55@hotmail.com");
                break;
            }
            case "Jurisdicción No. VI Ixmiquilpan":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.argelia_villareal));
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("759 723 00 88");
                direccionGestorTextView.setText("Calle francisco Javier Mina # 15 Col. El Carmen, Ixmiquilpan, Hidalgo");
                correoGestorTextView.setText("argevf80@hotmail.com");
                break;
            }
            case "Jurisdicción No. VII Actopan":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.patricia_lizeth_artegaga));
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("772 727 06 54 ó 772 727 93 31");
                direccionGestorTextView.setText("Lerdo de Tejada # 17, Centro Norte C. P. 42500, Actopan, Hidalgo");
                correoGestorTextView.setText("Enigm123@hotmail.com");
                break;
            }
            case "Jurisdicción No. VIII Metztitlan":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.emanuel_nemorio));
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("774 743 09 26");
                direccionGestorTextView.setText("Calzada del Refugio s/n Col. El Cerrito de Quimixtepec, Meztitlan, C.");
                correoGestorTextView.setText("clenemo@gmail.com");
                break;
            }
            case "Jurisdicción No. IX Molango":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.lizeth_monter_mohedano));
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("774 745 01 77");
                direccionGestorTextView.setText("Av. Juarez # 8 Molango Hidalgo");
                correoGestorTextView.setText("zherezada_211@hotmail.com");
                break;
            }
            case "Jurisdicción No. X Huejutla":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.mario_cerecedo_medina));
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("789 896 44 60 ó 789 896 58 83");
                direccionGestorTextView.setText("Calle la Lomita # 1, Col. La Lomita, C. P. 43000, Huejutla, Hidalgo");
                correoGestorTextView.setText("prospera.JHU@ssh.gob.mx");
                break;
            }
            case "Jurisdicción No. XI Apan":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.julio_cesar_moreno_palomares));
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("791 913 27 52 ó 791 916 95 64");
                direccionGestorTextView.setText("Zaragoza # 2 Col. Dina Ciudad Sahagun, Tepeapulco, Hidalgo C. P. 43995");
                correoGestorTextView.setText("julio_icsa2013@hotmail.com");
                break;
            }
            case "Jurisdicción No. XII Tizayuca":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.gabriela_aleman));
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("779 796 41 02");
                direccionGestorTextView.setText("Juarez sur # 36, Centro C. P. 43800 Tizayuca, Hidalgo");
                correoGestorTextView.setText("alemany1018@hotmail.com");
                break;
            }
            case "Jurisdicción No. XIII Otomí Tepehua":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.efrain_cabrera_escamilla));
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("776 755 39 61 ó 776 755 39 62");
                direccionGestorTextView.setText("Calle 16 de enero s7n Col. Centro, Tenango de Doria, C. P. 434080");
                correoGestorTextView.setText("efrain.cabrera.otomitepehua@gmail.com");
                break;
            }
            case "Jurisdicción No. XIV, Tepeji Del Rio":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.monica_gomez_blancarte));
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("778 737 11 26");
                direccionGestorTextView.setText("Av. Reforma 100 Col. Apepechoca, Tlaxcoapan");
                correoGestorTextView.setText("blancarte_240190@hotmail.com");
                break;
            }
            case "Jurisdicción No. XV, Atotonilco El Grande":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.javier_enrique_estrada));
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("774 743 07 60 ó 771 240 53 24");
                direccionGestorTextView.setText("Av. Cuahutemoc # 317 esq. Lic. Jorge Viesca Palma Col. Centro C.P. 43300");
                correoGestorTextView.setText("javier_psicologo87@hotmail.com");
                break;
            }
            case "Jurisdicción No. XVI, Jacala":
            {
                //gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.Ge));
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("441 293 30 67");
                direccionGestorTextView.setText("Calle Juventino Rosas # 106 Col. El Cerrito, Jacala de Ledezma C. P. 42200");
                correoGestorTextView.setText("sid_halo19@hotmail.com");
            }
            case "Jurisdicción No. XVII, Zacualtipán":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.denisse_sarahi));
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("774 742 05 99");
                direccionGestorTextView.setText("Av. Los Pinos # 1 Col. Los Pinos Zacualtipan, Hidalgo");
                correoGestorTextView.setText("renisss_2623@hotmail.com");
                break;
            }





            case "H. Obstetrico Pachuca":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.monica_rodriguez_vargas));
                nombreGestorTextView.setText("Dra Mónica Rodríguez Vargas");
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("771 716 61 92 ó 771 716 61 93");
                direccionGestorTextView.setText("AVENIDA PIRACANTOS ESQUINA SOLIDARIDAD S/N, COL. PIRACANTOS, MUN. PACHUCA DE SOTO, C. P. 42088");
                correoGestorTextView.setText("monic127@hotmail.com");

                break;
            }
            case "H. G. Actopan":
            {
                //gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.cas));
                nombreGestorTextView.setText("Dra. Pamela Castañeda Brito");
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("772 727 21 73 ó 772 727 17 96");
                direccionGestorTextView.setText("CALLE CARLOS MAYORGA55,COLONIA CHAPULTEPEC, PROLONGACIÓN CONSTITUCIÓN Y CHAPULTEPEC");
                correoGestorTextView.setText("dra.pamela.casbri@gmail.com");
                break;
            }
            case "H. G. Apan":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.christian_banos_kimenez));
                nombreGestorTextView.setText("Dr. Christian Jonathan Baños Jiménez");
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("748 912 72 77 (URG)\n" +
                        "748 912 72 51(ADMÓN)");
                direccionGestorTextView.setText("LIBRAMIENTO VIAL PROLONGACION CALLE HIDALGO S/N AMPLIACION SAN RAFAEL, LIBRAMIENTO A APAN COL CD. DEPORTIVA DE APAN");
                correoGestorTextView.setText("dr.christiancb@gmail.com");

                break;
            }
            case "H. G. Huichapan":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.jenny_alejandra));
                nombreGestorTextView.setText("Dra. Jenny Alejandra Hernández Razo");
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("761 782 01 68");
                direccionGestorTextView.setText("16 DE ENERO ESQUINA JORGE ROJO LUGO S/N, COL. BARRIO ABUNDIO MARTINEZ, MUN. HUICHAPAN, C. P. 42400");
                correoGestorTextView.setText("jenny.hernandez.razo@gmail.com");

                break;
            }
            case "H. G. Pachuca":
            {
                //gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable));
                nombreGestorTextView.setText("Alejandra Guadalupe Ramírez Reyes");
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("771 714 24 75 Ó 771 713 73 55");
                direccionGestorTextView.setText("CARRETERA PACHUCA TULANCINGO # 101, COL. CIUDAD DE LOS NIÑOS, MUN. PACHUCA DE SOTO, C. P. 42070");
                correoGestorTextView.setText("gestoreseguro.hgp@gmail.com");

                segundoGestorLinearLayout.setVisibility(View.VISIBLE);

                segundoGestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.miriam_acosta_vite));
                nombreSegundoGestorTextView.setText("Dra. Miriam Acosta Vite");
                turnoSegundoGestorTextView.setText("Sábados y Domingos");
                telefonoSegundoGestorTextView.setText("771 714 24 75 Ó 771 713 73 55");
                correoGestorTextView.setText("dra.mir.acosta@gmail.com");
                break;
            }
            case "H. G. Tulancingo":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.erick_munoz_lechuga));
                nombreGestorTextView.setText("Dr. Erick Muñoz Lechuga");
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("7558520 Ext. 1037");
                direccionGestorTextView.setText("PAXTEPEC, SANTIAGO TULANTEPEC, C. P. 43760, HIDALGO");
                correoGestorTextView.setText("eml28@livecom.mx");

                break;
            }
            case "H. R. Ixmiquilpan":
            {
                //gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.pa));
                nombreGestorTextView.setText("Dr. Ángel Omar Palacios Reséndiz");
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("759 727 12 38 ó 759 727 12 39");
                direccionGestorTextView.setText("CARRETERA PACHUCA-IXMIQUILPAN KM. 64 S/N, COL. TAXADHO, MUN. IXMIQUILPAN, C. P. 42302");
                correoGestorTextView.setText("omarpalaciosrdz@gmail.com");

                break;
            }
            case "H. G. Tula":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.ana_yanci));
                nombreGestorTextView.setText("Dra. Ana Yanci Tapia Valdez");
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("773 732 14 44 ó 773 732 09 61");
                direccionGestorTextView.setText("CARRETERA TULA-TEPEJI KM. 1.5 ENTRONQUE EL CARMEN - JOROBAS, COL. EL CARMEN, MUN. TULA DE ALLENDE, C. P. 42830");
                correoGestorTextView.setText("anytapval@gmail.com");
                break;
            }
            case "H. I. Jacala":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.elizabeth_ardelia));
                nombreGestorTextView.setText("Dra. Elizabeth Ardelia Aguilar Ramírez");
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("441 293 33 75 ó 441 293 33 76");
                direccionGestorTextView.setText("JUVENTINO ROSAS S/N, COLONIA EL CERRITO, Jacala");
                correoGestorTextView.setText("elizabethardelia@hotmail.com");

                break;
            }
            case "H.I. Cinta Larga":
            {
                //gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.denisse_sarahi));
                nombreGestorTextView.setText("Actualmente no cuenta con gestor");
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("738 725 36 60 ó 738 725 36 04");
                direccionGestorTextView.setText("CARRETERA MIXQUIAHUALA-TULA #3000, COL. CINTA LARGA SECCIÓN 22, MUN. MIXQUIAHUALA DE JUÁREZ, C. P. 42700");
                correoGestorTextView.setText("");

                break;
            }
            case "H. R. Huejutla":
            {
                gestorImageView.setImageDrawable(getResources().getDrawable(R.drawable.alejandro_vazquez));
                nombreGestorTextView.setText("Dr. Alejandro Vázquez Martínez\n");
                turnoGestorTextView.setText("Lunes a Viernes Matutino");
                telefonoGestorTextView.setText("789 893 3000");
                direccionGestorTextView.setText("CARRETERRA FEDERAL MÉXICO-TAMPICO KM. 211.5 A UN COSTADO DEL ENTRONQUE A ATLAPEXCO, COL. EJIDO. CHILILICO, MUN. HUEJUTLA DE REYES, C. P. 43000");
                correoGestorTextView.setText("-madman91@hotmail.com");

                break;
            }
        }

    }

    /**
     * Metodo que realiza el evento del boton back y que reliza la navegación hacia atras
     */
    @Override
    public void onBackPressed() {
        startActivity(new Intent(InformacionGestor.this, MainActivity.class));
    }
}
