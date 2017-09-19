package com.repss.apprepss;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

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

/**
 * Created by maste on 12/06/2017.
 */

public class Requests {

    public boolean Login(String response, String folio, String consecutivo, Context context){

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


                        if (folio.equals(beneficiario.getFolio()) && consecutivo.equals(beneficiario.getConsecutivo())) {
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
                            Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show();
                        }


                    }
                }
            }else{
                bandera = false;
                Toast.makeText(context, "Usted no está registrado en el sistema", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*if(!(response1.getPropertyCount() == 0)) {
            SoapObject response2 = (SoapObject) response1.getProperty("NewDataSet");
            SoapObject response3 = (SoapObject) response2.getProperty("consulta");

            beneficiario.setFolio(response3.getProperty("ClaveSP").toString());
            beneficiario.setConsecutivo(response3.getProperty("Consecutivo").toString());
            beneficiario.setNombres(response3.getProperty("Nombres").toString());
            beneficiario.setApellidoPaterno(response3.getProperty("ApellidoPaterno").toString());
            beneficiario.setApellidoMaterno(response3.getProperty("ApellidoMaterno").toString());
            beneficiario.setCURP(response3.getProperty("CURP").toString());
            beneficiario.setDependenciaSalud(response3.getProperty("NombreUnidadSalud").toString());
            beneficiario.setCLUES(response3.getProperty("CLUES").toString());

            if (response3.getProperty("Sexo").toString().equals("H"))
                beneficiario.setSexo("Hombre");
            else if (response3.getProperty("Sexo").toString().equals("M"))
                beneficiario.setSexo("Mujer");

            String[] fechaVencimiento = response3.getProperty("FechaFinDerechohabiencia").toString().split("T");
            String[] fechaNacimiento= response3.getProperty("FechaNacimiento").toString().split("T");


            if (folio.equals(beneficiario.getFolio()) && consecutivo.equals(beneficiario.getConsecutivo())) {
                SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd");
                Date fechaDate1 = null;
                fechaDate1 = formateador.parse(fechaVencimiento[0].replace("-","/"));
                beneficiario.setFechaVencimiento(fechaDate1);

                SimpleDateFormat formateador2 = new SimpleDateFormat("yyyy/MM/dd");
                Date fechaDateNac = null;
                fechaDateNac = formateador.parse(fechaNacimiento[0].replace("-","/"));

                long diff = Math.abs(getDate().getTime() - fechaDateNac.getTime());
                long diffDays = diff / (24 * 60 * 60 * 1000);

                beneficiario.setEdad(String.valueOf(diffDays/365));


                bandera = true;
                Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            bandera = false;
            Toast.makeText(context, "Usted no está registrado en el sistema", Toast.LENGTH_SHORT).show();
        }*/

        return bandera;
    }


    private boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }

        return false;
    }

    private Date getDate(){
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

}
