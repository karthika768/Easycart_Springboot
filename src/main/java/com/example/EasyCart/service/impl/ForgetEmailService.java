/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service.impl;

import com.example.EasyCart.form.ForgetPasswordForm;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author acer
 */
@Service
public class ForgetEmailService {

//    @Autowired
//    private ForgetPasswordForm  forgetPasswordForm;
    public boolean sendMail(String subject, String message, String to) {

        boolean f = false;
        String from = "aeasy462@gmail.com";
        //variable for email
        String host = "smtp.gmail.com";
//        subject="Forget Password";
//        message = "your otp is ..";
//        to =forgetPasswordForm.getEmail();

        Properties properties = System.getProperties();
        System.out.println(properties);

        //setting info to propertties object
        //set host
        properties.put("mail.smtp.host", host);
        //set port
//        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //get the session object
        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("aeasy462@gmail.com", "zdhzjzqornuqnmjp"); //To change body of generated methods, choose Tools | Templates.
            }

        });
        //Compose message
        MimeMessage mimeMessage = new MimeMessage(session);

        try {
            //from email
            mimeMessage.setFrom(from);
            // adding recipient to message
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //adding subject to message
            mimeMessage.setSubject(subject);
            // adding text to msg
            // mimeMessage.setText(message);
            mimeMessage.setContent(message, "text/html");

            Transport.send(mimeMessage);
            System.out.println("Sent");
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
