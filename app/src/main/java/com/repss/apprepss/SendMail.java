package com.repss.apprepss;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by maste on 08/06/2016.
 */
public class SendMail extends AsyncTask<Void,Void,Void> {

    private Context context;
    private Session session;

    private EditText edtContacto,edtDenuncia;

    //Information to send email
    private String email;
    private String subject;
    private String message;

    //Progressdialog to show while sending email
    //private ProgressDialog progressDialog;

    //Class Constructor
    public SendMail(Context context, String email, String subject, String message, EditText editTextContacto, EditText editTextDenuncia){
        //Initializing variables
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.edtContacto = editTextContacto;
        this.edtDenuncia = editTextDenuncia;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //progressDialog = ProgressDialog.show(context,"Sending message","Please wait...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //progressDialog.dismiss();
        //Showing a success message
        Toast.makeText(context,"Solicitud enviada",Toast.LENGTH_LONG).show();

        edtContacto.setText("");
        edtDenuncia.setText("");
    }

    @Override
    protected Void doInBackground(Void... params) {

        Properties props = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //Creating a new session
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("desarrolloprospectiva@gmail.com", "Prospectiva123");
                    }
                });

        try {
            //Creating MimeMessage object
            MimeMessage mm = new MimeMessage(session);

            //Setting sender address
            mm.setFrom(new InternetAddress("desarrolloprospectiva@gmail.com"));
            //Adding receiver
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            //Adding subject
            mm.setSubject(subject);
            //Adding message
            mm.setText(message);

            //Sending email
            Transport.send(mm);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
