package com.kuli.marathamatrimony.Activity;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailAPI extends AsyncTask<Void, Void, Void> {

    private Context context;

    private Session session;
    private String email, PhoneNum, fullname, pass, ms, mt, rlgn, highestEd, degreeEd, employed, occu, incm, MF, DOB, Height, country, state, city;

    public JavaMailAPI(Context context, String email, String PhoneNum, String fullname, String pass,String  ms,String  mt,String rlgn,String highestEd,String degreeEd,
                       String employed,String occu,String incm,String MF,String DOB,String Height,String country,String state, String city) {
        this.context = context;
        this.email = email;
        this.PhoneNum = PhoneNum;
        this.fullname = fullname;
        this.pass = pass;
        this.ms = ms;
        this.mt = mt;
        this.rlgn = rlgn;
        this.highestEd = highestEd;
        this.degreeEd = degreeEd;
        this.employed = employed;
        this.occu = occu;
        this.incm = incm;
        this.MF = MF;
        this.DOB = DOB;
        this.Height = Height;
        this.country = country;
        this.state = state;
        this.city = city;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Utils.EMAIL, Utils.PASSWORD);
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(Utils.EMAIL));
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(email)));
            mimeMessage.setSubject("JeevanSathiSearch User Data");
            mimeMessage.setText("Full Name :"+fullname+"\n" +"Phone Number :"+PhoneNum +"\n" +"Email Id :"+email+"\n" +"Password :"+pass+"\n" + "Marital Status :"+ms+"\n" + "Mother Tongue :"+mt
                    +"\n" + "Religion :"+rlgn+"\n" +"Highest Education :"+ highestEd+"\n" +"Degree Education :"+ degreeEd+
                    "\n" + "Employed In :"+employed+"\n" +"Occupation :"+ occu+"\n" +"Income :"+ incm+"\n" + "Gender :"+MF+"\n" +"Date of Birth :"+DOB+"\n" + "Height :"+Height
                    +"\n" +"Country :"+ country+"\n" +"State :"+state+"\n" +"City :"+city);

            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        MimeMessage mimeMessage1 = new MimeMessage(session);
        try {
            mimeMessage1.setFrom(new InternetAddress(Utils.EMAIL));
            mimeMessage1.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress("jeevansathisearch007@gmail.com")));
            mimeMessage1.setSubject("JeevanSathiSearch User Data");
            mimeMessage1.setText("Full Name :"+fullname+"\n" +"Phone Number :"+PhoneNum +"\n" +"Email Id :"+email+"\n" +"Password :"+pass+"\n" + "Marital Status :"+ms+"\n" + "Mother Tongue :"+mt
                    +"\n" + "Religion :"+rlgn+"\n" +"Highest Education :"+ highestEd+"\n" +"Degree Education :"+ degreeEd+
                    "\n" + "Employed In :"+employed+"\n" +"Occupation :"+ occu+"\n" +"Income :"+ incm+"\n" + "Gender :"+MF+"\n" +"Date of Birth :"+DOB+"\n" + "Height :"+Height
                    +"\n" +"Country :"+ country+"\n" +"State :"+state+"\n" +"City :"+city);

            Transport.send(mimeMessage1);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;

    }
}
