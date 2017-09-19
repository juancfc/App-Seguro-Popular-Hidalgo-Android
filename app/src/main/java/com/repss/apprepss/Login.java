package com.repss.apprepss;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/*import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;*/
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.microsoft.windowsazure.notifications.NotificationsManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Login extends AppCompatActivity {

    AppSettings appSettings = new AppSettings();
    //Requests requestsSP = new Requests();
    Beneficiario beneficiario = new Beneficiario();

    EditText edtPoliza,edtCURP;
    Button btnLogin;
    RelativeLayout rltLayoutMain;

    TextView txvIrCurp, noAfiliadoTextView;

    public static Boolean isVisible = false;
    private static final String TAG = "MainActivity";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    String tag;

    DataBaseManager manager;
    DbHelper helper;
    Cursor cursor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //requestsSP = new RequestsSP();


        insertCA(this);

        final SharedPreferences settings = getSharedPreferences("UserSettings", Context.MODE_PRIVATE);

        if(settings.getBoolean("userSesion",false)){
            Intent properties = new Intent(Login.this, MainActivity.class);
            startActivity(properties);
        }

        edtPoliza = (EditText) findViewById(R.id.edtPoliza);
        edtCURP = (EditText) findViewById(R.id.edtCURP);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        txvIrCurp = (TextView)findViewById(R.id.txvIrCURP);
        noAfiliadoTextView = (TextView)findViewById(R.id.noAfiliadoTextView);


        txvIrCurp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://consultas.curp.gob.mx/CurpSP/inicio2_2.jsp";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        noAfiliadoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, NoAfiliado.class));
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new LoginService().execute();

                //startActivity(new Intent(Login.this,MainActivity.class));

            }
        });

    }

    public void insertCA(Context context) {
        manager = new DataBaseManager(getApplicationContext());
        helper = new DbHelper(getApplicationContext());
        final SQLiteDatabase db = helper.getReadableDatabase();

        //Instruccion para realizar la consulta y almacenarlo en un cursor
        cursor = db.rawQuery(" SELECT * FROM centros_afiliacion;", null);

        if (cursor.getCount() < 1) {
            manager.InsertCA("Coordinación Estatal Pachuca", "Camino Real de la Plata. No. 322 ,  Colonia Zona Plateada, Pachuca Hgo.",
                    "7711383445, ext. 13100", "Angélica Eloísa Mena Islas", "7715679393", "Lunes a Domingo de 8:00 a 19:00 hrs", "maohgo_repss@hotmail.com", "20.097428", "-98.778333");
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
            manager.InsertCA("Hospital General de Tula", "Carr. Tula- Tepejí km. 1.5 entronque Lib. Refineria-Tepeji Col. El Carmen, Tula de Allende Hgo. C.P. 42830",
                    "7737321444", "Anabel Hernández Cruz", "7731049490", "Lunes a Domingo de 8:00 a 19:00 hrs", "maohgo_tula@hotmail.com", "20.04046", "-99.34255");
            manager.InsertCA("Centro de salud Tula", "Av. 5 de febrero No. 5, Col. Centro, Tula de Allende, Hgo., C.P. 42800",
                    "7731003227", "José Guillermo Juarez de la Cruz", "7711426302", "Martes y Jueves de 8:00 a 16:00 hrs.", "maohgo_cstula@hotmail.com", "20.05425", "-99.34052");
            manager.InsertCA("Hospital General Huichapan", "C. 16 de Enero s/n. Esq. Jorge Rojo Lugo. Barrio Abundio Martinez",
                    "7617820168", "Liliana Hernández Chávez", "7731103157", "Lunes a Domingo de 8:00 a 19:00 hrs", "maohgo_huichapan@hotmail.com", "20.38275", "-99.65514");
            manager.InsertCA("Centro de salud Zimapan", "Av. Heroico Colegio Militar, sn col centro zimapan hgo.",
                    "7597282905, 7597282183", "María Del Carmen Salinas Acosta ", "7712344018", "Lunes a Viernes de 8:00 a 16:00 hrs.", "maohgo_zimapan@hotmail.com", "20.73414", "-99.38111");
            manager.InsertCA("Hospital del Valle del Mezquital", "Carr. Pachuca Ixmiquilpan km. 64, s/n. C.P. 42320",
                    "7597271238, 7271239", "Miriam Lizeth Lugo Bautista", "7721252768", "Lunes a Domingo de 7:00 a 19:00 hrs", "maohgo_ixmqiuilpan@hotmail.com", "20.43318", "-99.1556");
            manager.InsertCA("Centro de salud Ixmiquilpan", "Calle Allende #1 Col. Centro, Ixmiquilpan, Hgo.",
                    "s/n", "Nelly Ximena Rodríguez Ramírez", "7711303864", "Lunes a Viernes de 8:00 a 16:00 hrs.", "maohgo_csixmiquilpan@hotmail.com", "20.47929", "-99.21881");
            manager.InsertCA("Centro de salud Tasquillo", "",
                    "", "", "", "Horario: Lunes de 08:00 a 16:00 hrs.", "", "20.55217", "-99.31074");
            manager.InsertCA("Hospital General de Actopan", "Carlos Mayorga # 55, Col. Chapultepec,Actopan,Hgo.",
                    "0177271796, 0177272173", "Israel Hidalgo Pérez", "7713068333", "Lunes a Domingo de 7:00 a 19:00 hrs", "maohgo_actopan@hotmail.com", "20.26273", "-98.93973");
            manager.InsertCA("Hospital Integral Cinta Larga", "Carretera Mixquiahuala-tula km 28 # 3000 cinta larga. Mixquiahuala de Juárez Hgo.",
                    "7387253604, 7387253660", "Efraín León Cruz", "7717769745", "Lunes a Viernes de 7:00 a 20:00 hrs y Sabado y Domingo de 7:00 a 19:00 hrs.", "maohgo_cintalarga@hotmail.com", "20.18922", "-99.22501");
            manager.InsertCA("Centro de Salud Rural Concentrado Metztitlan", "Avenida Issac Piña s/n",
                    "7747430859", "Angélica Cruz Reyes", "7717005166", "Lunes a Viernes de 8:00 a 16:00 hrs.", "mao_metztitlan@hotmail.com", "20.6", "-98.76666");
            manager.InsertCA("Centro de salud Tlahuiltepa", "Pascual Morales Colonia Centro",
                    "s/n", "Nohemi Acosta Zenil", "7711263679", "Lunes a Viernes de 8:00 a 16:00 hrs.", "acostazen@hotmail.com", "20.92478", "-98.95021");
            manager.InsertCA("Centro de salud Tlanchinol", "Barrio, Unidad Deportiva, Tlanchinol, Hidalgo",
                    "7749740242", "Bartolo Antonio Ignacio", "7712727495", "Domingo a Viernes de 8:00 a 16:00 hrs.", "maohgo_tlanchinol@hotmail.com", "20.98498", "-98.66748");
            manager.InsertCA("Centro de salud Tepehuacan de Guerrero", "Avenida de la Democracia s/n",
                    "7712036040", "Rafael Santiago Ávila", "7711281305", "Lunes a Viernes de 8:00 a 16:00 hrs.", "maohgo_tepehuacan@hotmail.com", "21.01372", "-98.84171");
            manager.InsertCA("Centro de salud Calnali", "Barrio Tlala Av. Principal Cuautemoc S/N, Calnali Hidalgo.",
                    "s/n", "Gerardo González Ángeles", "7711055859", "Lunes a Viernes de 8:00 a 16:00 hrs.", "g.era.ga@hotmail.com", "20.89715", "-98.58004");
            manager.InsertCA("Centro de salud Molango", "Av. Hidalgo Barrio Santa Cruz 2da Seccion Molango de Escamilla Hidalgo",
                    "s/n", "Eva Itzel Pérez Acosta", "7711796986", "Lunes a Viernes de 8:00 a 16:00 hrs", "maohgo_cs.molango@hotmail.com", "20.78556", "-98.72595");
            manager.InsertCA("Hospital General Huejutla", "Carretera. Federal. Mexico-Tampico Km 2011.5 Ejido Chililico, Huejutla, Hgo.",
                    "7898933000", "Feliciano Hernández Ramírez ", "7711841531", "Lunes a Domingo de 8:00 a 20:00 hrs", "maohgo_huejutla@hotmail.com", "21.10481", "-98.4242");
            manager.InsertCA("Centro de salud Huejutla", "Calle. Jaime Nunó No. 21 Col. Capitan Antonio Reyes, Huejutla, Hgo.",
                    "7898960380", "Guadalupe Zuñiga Juárez ", "7711256355", "Lunes a Viernes de 8:00 a 16:00 hrs.", "mao_cshuejutla@hotmail.com", "21.13732", "-98.42269");
            manager.InsertCA("Centro de salud San Felipe Orizatlan", "Dr. Niños Héroes s/n Col. Centro, San Felipe Orizatlan.",
                    "s/n", "Maria Oliva Saab Fernández", "7712140548", "Lunes a Viernes de 8:00 a 16:00 hrs.", "mao_cssanfelipe@hotmail.com", "21.16827", "-98.60616");
            manager.InsertCA("Centro de salud Tepeapulco", "Av. Felipe angeles s/n col 18 de marzo",
                    "7919150475", "Janice Hernández Melo", "7711789545", "Lunes a Viernes de 8:30 a 16:30 hrs.", "maohgo_cstepeapulco@hotmail.com", "19.78454", "-98.55825");
            manager.InsertCA("Centro de salud Acayuca", "c. Venustiano Carranza s/n col. Lomas del Pedregal,",
                    "s/n", "Judith Cecilia Cueto Rodríguez", "7713234101", "Martes de 08:00 a 16:00 hrs.", "mao_ruta_tizayuca@hotmail.com", "20.02815", "-98.84178");
            manager.InsertCA("Centro de salud Villa de Tezontepec", "Av. Hidalgo s/n Col.centro",
                    "s/n", "Judith Cecilia Cueto Rodríguez", "7713234101", "Lunes de 08:00 a 16:00 hrs.", "mao_ruta_tizayuca@hotmail.com", "19.88345", "-98.82413");
            manager.InsertCA("Centro de salud Tizayuca", "Avenida 5 de Mayo #22. Col. El Pedregal. Tizayuca.",
                    "7797962051", "Julio César Martínez Roldan", "7711891295", "Lunes a Domingo de 8:00 a 19:00 hrs", "maohgo_tizayuca@hotmail.com", "19.84347", "-98.97942");
            manager.InsertCA("Centro de salud Zapotlán", "Narcizo Mendoza #3 Col. Centro, Zapotlán de Juárez",
                    "7713234101", "Judith Cecilia Cueto Rodríguez", "7713234101", "Miercoles de 08:00 a 16:00 hrs.", "mao_ruta_tizayuca@hotmail.com", "19.97738", "-98.86412");
            manager.InsertCA("Centro de salud Tolcayuca", "Av. Independencia s/n col. Centro",
                    "s/n", "Judith Cecilia Cueto Rodríguez", "7713234101", "Jueves de 08:00 a 16:00 hrs.", "mao_ruta_tizayuca@hotmail.com", "19.95604", "-98.91763");
            manager.InsertCA("Centro de salud Zempoala", "C. Pirul #100 col. La Salida",
                    "s/n", "Judith Cecilia Cueto Rodríguez", "7713234101", "Viernes de 08:00 a 16:00 hrs.", "mao_ruta_tizayuca@hotmail.com", "19.91593", "-98.67059");
            manager.InsertCA("Hospital Integral Otomi-Tepehua", "carretera Tulancingo de Bravo- San Bartolo Tutotepec, Km 13, C.P. 43440, s/n, Col. La Magdalena. San Bartolo Tutotepec, Hgo.",
                    "7747553263, 7747553264", "Uziel Tellez Hernandez", "7712991123", "Lunes a Viernes de 8:00 a 16:00 hrs y Sabado y Domingo de 8:00 a 20:00 hrs.", "maohgo_sanbartolo@hotmail.com", "20.39517", "-98.20342");
            manager.InsertCA("Hospital Basico Huehuetla", "carretera Tulancingo de Bravo - Huehuetla, c.p 43420 , s/n, Col. Linda Vista (mira sol). Huehuetla, Hgo.",
                    "7747437177, 7747437299", "Salvador Bautista Reyes", "7712049892", "Horario: Lunes a Viernes de 8:00 a 16:00 hrs.", "maohgo_huehuetla@hotmail.com", "20.46059", "-98.06748");
            manager.InsertCA("Centro de salud Tenango de Doria", "C. Cornelio Mendoza,s/n,col. Centro,tenango de doria,c.p. 43480",
                    "s/n", "Liliana Huerta Farías", "7711893123", "Lunes a Viernes de 8:00 a 16:00 hrs.", "maohgo_cstenango@hotmail.com", "20.33819", "-98.22591");
            manager.InsertCA("Centro de salud Acaxochitlán", "C. Cuautémoc sn Barrio Tlatempa, Acaxochitlan.",
                    "7767520322", "Adriana Montiel Cruz", "7711299073", "Lunes a Viernes de 8:00 a 16:00 hrs.", "maohgo_csacaxochitlan@hotmail.com", "20.16036", "-98.20365");
            manager.InsertCA("Centro de salud Tepeji del Rio", "Calle de la Salud No. 2, Col. Tlaxinacalpan, mpio. Tepeji del Río, Hgo., C.P. 42850",
                    "7737330624", "Griselda Bautista Pérez", "7731502347", "Lunes a Viernes de 8:00 a 16:00 hrs.", "maohgo_tepeji@hotmail.com", "19.9216", "-99.34188");
            manager.InsertCA("Centro de salud Tlaxcoapan", "Av. Miguel Hidalgo S/N Col. Educación, CP. 42952 Tlaxcoapan, Hgo.",
                    "7787370037", "Griselda Flores García", "7712794712", "Lunes a Viernes de 8:30 a 19:00 hrs. y Sabado y Domingo de 7:00 a 19:00 hrs.", "maohgo_tlaxcoapan@hotmail.com", "20.09144", "-99.22802");
            manager.InsertCA("Centro de salud Atitalaquia", "Av. Norte No. 6, Col. Centro, Atitalaquia, Hgo. C.P. 42970",
                    "7787374199", "Leonardo Elionary Vázquez Reyes", "7737364232", "Lunes y Viernes de 8:30 a 16:30 hrs.", "mao_ruta_tepeji@hotmail.com", "20.06024", "-99.22188");
            manager.InsertCA("Centro de salud Tetepango", "C. Roman Monroy s/n Barrio Nicolas Bravo",
                    "s/n", "Leonardo Elionary Vázquez Reyes", "7737364232", "Martes de 8:30 a 16:30 hrs", "mao_ruta_tepeji@hotmail.com", "20.10051", "-99.15771");
            manager.InsertCA("Centro de salud Atotonilco de Tula", "C. Republica del Salvador # 8 col. Centro",
                    "s/n", "Leonardo Elionary Vázquez Reyes", "7737364232", "Miercoles de 8:30 a 16:30 hrs.", "mao_ruta_tepeji@hotmail.com", "20.01155", "-99.22156");
            manager.InsertCA("Centro de salud Ajacuba", "Carr.Tula- Pachuca s/n col. El estanque",
                    "s/n", "Leonardo Elionary Vázquez Reyes", "7737364232", "Jueves de 8:30 a 16:30 hrs.", "mao_ruta_tepeji@hotmail.com", "20.09369", "-99.12431");
            manager.InsertCA("Centro de salud Atotonilco el Grande", "Carr. México- Tampico KM. 16,5 Tiltepec",
                    "s/n", "Olga Pérez Mendoza", "7711755221", "Lunes a Viernes de 8:00 a 16:00 hrs.", "mahgo_csatotonilco@hotmail.com", "20.30481", "-98.68294");
            manager.InsertCA("Centro de salud Huasca de Ocampo", "C. vicente Guerrero # 2 col. Centro",
                    "s/n", "Erick Gil Sánchez", "7711724761", "Lunes y Jueves de 8:00 a 16:00 hrs.", "mao_ruta_atotonilco@hotmail.com", "20.20455", "-98.57563");
            manager.InsertCA("Centro de salud Mineral del Chico", "C. Ignacio Zaragoza s/n Barrio centro",
                    "s/n", "Erick Gil Sánchez", "7711724761", "Martes de 8:00 a 16:00 hrs.", "mao_ruta_atotonilco@hotmail.com", "20.21437", "-98.7309");
            manager.InsertCA("Centro de salud Omitlán", "C. Coronel Jose Maria Perez # 2 Col. Centro.",
                    "s/n", "Erick Gil Sánchez", "7711724761", "Miercoles de 08:00 a 16:00 hrs.", "mao_ruta_atotonilco@hotmail.com", "20.16968", "-98.64892");
            manager.InsertCA("Centro de salud Acatlán", "C. Independencia s/n col centro",
                    "s/n", "Erick Gil Sánchez", "7711724761", "Viernes de 08:00 a 16:00 hrs.", "mao_ruta_atotonilco@hotmail.com", "20.14464", "-98.43989");
            manager.InsertCA("Hospital Integral Jacala", "C. Juventino Rosas, S/N,Barrio. El Cerrito, Jacala, Hgo.",
                    "4412933376, 4412933375", "Crisol Marlem Díaz Méndez", "7711317978", "Lunes a Domingo de 8:00 a 20:00 hrs", "maohgo_jacala@hotmail.com", "21.01023", "-99.19423");
            manager.InsertCA("Centro de salud Pisaflores", "Av. Independecia S/N, Cuartel Morelos, Pisaflores, Hgo.",
                    "Coordinación 4833786257, C. S. 4833786078", "Miriam Nallely Rubio Díaz", "7711762925", "Lunes a Viernes de 8:00 a 16:00 hrs.", "maohgo_pisaflores@hotmail.com", "21.19243", "-99.00185");
            manager.InsertCA("Centro de salud Chapulhuacán", "Carretera México Laredo S/N, Chapulhuacan; Hgo.",
                    "s/n", "Juan Tavera Guerrero", "7711869986", "Lunes a Viernes de 8:00 a 16:00 hrs.", "mao_cschapulhuacan@hotmail.com", "21.15883", "-98.90744");
            manager.InsertCA("Centro de salud Zacualtipán", "Galeana No.2 Colonia Centro",
                    "7747421187", "Laura Hernández Solís", "7711493413", "Lunes a Viernes de 8:00 a 16:00 hrs.", "maohgo_cszacualtipan@hotmail.com", "20.64683", "-98.65556");
            manager.InsertCA("Hospital General del Altiplano", "Libramiento vial calle Prol.Hidalgo s/n col Ampliacion San Rafael Apan Hgo.",
                    "7489127277, 7489127251", "Mónica Cuellar Ramírez", "7751385422", "Lunes a Domingo de 7:00 a 20:00 hrs", "maohgo_apan@hotmail.com", "19.71363", "-98.46507");
            manager.InsertCA("Centro de salud Nicolas Flores", "",
                    "", "", "", "Viernes de 8:00 a 16:00 hrs", "", "20.76792", "-99.15013");
            manager.InsertCA("Centro de salud Cardonal", "",
                    "", "", "", "Miercoles y Jueves de 08:00 a 16:00 hrs.", "", "20.61426", "-99.11663");
            manager.InsertCA("Centro de salud Chilcuautla", "",
                    "", "", "", "Martes de 8:00 a 16:00 hrs", "", "20.33087", "-99.22826");


        }

    }

    private class LoginService extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            btnLogin.setEnabled(false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final SharedPreferences settings = getSharedPreferences("UserSettings", Context.MODE_PRIVATE);
                    if ((edtPoliza.getText().toString().length() < 10 || edtPoliza.getText().toString().length() > 10 ) && (edtCURP.getText().toString().equals(""))){
                        edtCURP.setError("Debe de ingresar su CURP");
                        if(edtPoliza.getText().toString().equals(""))
                            edtPoliza.setError("Debe ingresar su póliza");
                        else
                            edtPoliza.setError("La poliza que ingreso no es correcta");
                        btnLogin.setEnabled(true);
                    }else if(edtCURP.getText().toString().equals("")) {
                        edtCURP.setError("Debe de ingresar su CURP");
                        btnLogin.setEnabled(true);
                    }else if (edtPoliza.getText().toString().length() < 10 || edtPoliza.getText().toString().length() > 10 ) {
                        if(edtPoliza.getText().toString().equals(""))
                            edtPoliza.setError("Debe ingresar su póliza");
                        else
                            edtPoliza.setError("La poliza que ingreso no es correcta");
                        btnLogin.setEnabled(true);
                    } else {


                        final String url = "http://189.254.11.163:8080/consulta/ServicioWeb.asmx/busqueda_curp?folio=" + edtPoliza.getText().toString() +"&curp=" + edtCURP.getText().toString();

                        RequestQueue queue = Volley.newRequestQueue(getApplication());
                        StringRequest stringRequest;
                        stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                                new com.android.volley.Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Beneficiario beneficiario = new Beneficiario();

                                        boolean bandera = false;

                                        try {

                                            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                                            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                                            Document doc = dBuilder.parse(new InputSource( new StringReader(response)));

                                            //optional, but recommended
                                            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
                                            doc.getDocumentElement().normalize();

                                            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

                                            NodeList nList = doc.getElementsByTagName("consulta");

                                            if(!(nList.getLength() == 0)) {

                                                for (int temp = 0; temp < nList.getLength(); temp++) {

                                                    Node nNode = nList.item(temp);

                                                    System.out.println("\nCurrent Element :" + nNode.getNodeName());

                                                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                                                        Element eElement = (Element) nNode;


                                                        beneficiario.setFolio(eElement.getElementsByTagName("ClaveSP").item(0).getTextContent());
                                                        beneficiario.setConsecutivo(eElement.getElementsByTagName("Consecutivo").item(0).getTextContent());
                                                        beneficiario.setNombres(eElement.getElementsByTagName("Nombres").item(0).getTextContent());
                                                        beneficiario.setApellidoPaterno(eElement.getElementsByTagName("ApellidoPaterno").item(0).getTextContent());
                                                        beneficiario.setApellidoMaterno(eElement.getElementsByTagName("ApellidoMaterno").item(0).getTextContent());
                                                        beneficiario.setCURP(eElement.getElementsByTagName("CURP").item(0).getTextContent());
                                                        beneficiario.setDependenciaSalud(eElement.getElementsByTagName("NombreUnidadSalud").item(0).getTextContent());
                                                        beneficiario.setCLUES(eElement.getElementsByTagName("CLUES").item(0).getTextContent());


                                                        if (eElement.getElementsByTagName("Sexo").item(0).getTextContent().equals("H"))
                                                            beneficiario.setSexo("Hombre");
                                                        else if (eElement.getElementsByTagName("Sexo").item(0).getTextContent().equals("M"))
                                                            beneficiario.setSexo("Mujer");

                                                        String[] fechaVencimiento = eElement.getElementsByTagName("FechaFinDerechohabiencia").item(0).getTextContent().split("T");
                                                        String[] fechaNacimiento = eElement.getElementsByTagName("FechaNacimiento").item(0).getTextContent().split("T");


                                                        if (edtPoliza.getText().toString().equals(beneficiario.getFolio()) && edtCURP.getText().toString().equals(beneficiario.getCURP())) {
                                                            SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd");
                                                            Date fechaDate1 = null;
                                                            fechaDate1 = formateador.parse(fechaVencimiento[0].replace("-", "/"));
                                                            beneficiario.setFechaVencimiento(fechaDate1);

                                                            SimpleDateFormat formateador2 = new SimpleDateFormat("yyyy/MM/dd");
                                                            Date fechaDateNac = null;
                                                            fechaDateNac = formateador.parse(fechaNacimiento[0].replace("-", "/"));

                                                            long diff = Math.abs(getDate().getTime() - fechaDateNac.getTime());
                                                            long diffDays = diff / (24 * 60 * 60 * 1000);

                                                            beneficiario.setEdad(String.valueOf(diffDays / 365));


                                                            bandera = true;
                                                            Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                                                        }


                                                    }
                                                }
                                            }else{
                                                bandera = false;
                                                Toast.makeText(getApplicationContext(), "Usted no está registrado en el sistema", Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        if(bandera) {
                                            Intent properties = new Intent(Login.this, MainActivity.class);
                                            if(getDate().before(beneficiario.getFechaVencimiento())){
                                                beneficiario.setStatus("Activo");
                                            }
                                            else{
                                                beneficiario.setStatus("Necesita Reafiliación");
                                            }

                                            if(Integer.valueOf(beneficiario.getEdad())>= 10 && Integer.valueOf(beneficiario.getEdad()) < 19 ){
                                                tag = "Adolescente";
                                            }else if (Integer.valueOf(beneficiario.getEdad())>= 19 && Integer.valueOf(beneficiario.getEdad()) < 59){
                                                if(beneficiario.getSexo().equals("Hombre"))
                                                    tag = "AdultoHombre";
                                                else {
                                                    tag = "AdultoMujer";
                                                }
                                            }else  if (Integer.valueOf(beneficiario.getEdad())>= 60){
                                                tag = "AdultoMayor";
                                            }

                                            appSettings.SaveLoginInfo(settings,beneficiario.getFolio(),beneficiario.getConsecutivo(),beneficiario.getNombres(),beneficiario.getApellidoPaterno(),beneficiario.getApellidoMaterno(),beneficiario.getCURP(),beneficiario.getStatus(),beneficiario.getDependenciaSalud(),beneficiario.getCLUES(), getDateVencimiento(beneficiario.getFechaVencimiento()), beneficiario.getSexo(), beneficiario.getEdad(), tag);
                                            NotificationsManager.handleNotifications(getApplicationContext(), NotificationSettings.SenderId, MyHandler.class);
                                            registerWithNotificationHubs();
                                            startActivity(properties);
                                        }
                                        else{
                                            btnLogin.setEnabled(true);
                                        }
                                    }
                                }, new com.android.volley.Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Login.this, "Sucedio un error, revise su conexión a internet o vuelva a intentarlo más tarde.", Toast.LENGTH_SHORT).show();
                                btnLogin.setEnabled(true);
                            }
                        });

                        queue.add(stringRequest);


                        /*Intent properties = new Intent(Login.this, MapaCS.class);
                        beneficiario.setStatus("Activo");
                        appSettings.SaveLoginInfo(settings,"1314141414","1","Leonardo","Flores","Martinez","FLML800714HHGNPG04",beneficiario.getStatus(),"Hospital General Tulancingo","SLJAHKJ8932", "16/05/2017");
                        startActivity(properties);

                        if (requestsSP.Login(edtPoliza.getText().toString(),edtConsecutivo.getText().toString(),Login.this)) {

                            Intent properties = new Intent(Login.this, MainActivity.class);
                            if(getDate().before(beneficiario.getFechaVencimiento())){
                                beneficiario.setStatus("Activo");
                            }
                            else{
                                beneficiario.setStatus("Necesita Reafiliación");
                            }

                            if(Integer.valueOf(beneficiario.getEdad())>= 10 && Integer.valueOf(beneficiario.getEdad()) < 19 ){
                                tag = "Adolescente";
                            }else if (Integer.valueOf(beneficiario.getEdad())>= 19 && Integer.valueOf(beneficiario.getEdad()) < 59){
                                if(beneficiario.getSexo().equals("Hombre"))
                                    tag = "AdultoHombre";
                                else {
                                    tag = "AdultoMujer";
                                }
                            }else  if (Integer.valueOf(beneficiario.getEdad())>= 60){
                                tag = "AdultoMayor";
                            }

                            appSettings.SaveLoginInfo(settings,beneficiario.getFolio(),beneficiario.getConsecutivo(),beneficiario.getNombres(),beneficiario.getApellidoPaterno(),beneficiario.getApellidoMaterno(),beneficiario.getCURP(),beneficiario.getStatus(),beneficiario.getDependenciaSalud(),beneficiario.getCLUES(), getDateVencimiento(beneficiario.getFechaVencimiento()), beneficiario.getSexo(), beneficiario.getEdad(), tag);
                            NotificationsManager.handleNotifications(getApplicationContext(), NotificationSettings.SenderId, MyHandler.class);
                            registerWithNotificationHubs();
                            startActivity(properties);
                        }
                        else{
                            btnLogin.setEnabled(true);
                        }*/

                    }
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aBoolean) {

        }
    }

    private Date getDate(){
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    private String getDateVencimiento(Date fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fechaConFormato = sdf.format(fecha);
        return fechaConFormato;
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(startMain);
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported by Google Play Services.");
                ToastNotify("This device is not supported by Google Play Services.");
                finish();
            }
            return false;
        }
        return true;
    }



    public void registerWithNotificationHubs()
    {
        if (checkPlayServices()) {
            // Start IntentService to register this application with FCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }

    public void ToastNotify(final String notificationMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Login.this, notificationMessage, Toast.LENGTH_LONG).show();
            }
        });
    }


}
