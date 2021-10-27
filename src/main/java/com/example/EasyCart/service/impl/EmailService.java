/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service.impl;

import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.entity.VerificationToken;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author arun
 */
@Service
public class EmailService {
    private final VerificationTokenServiceImpl verificationTokenServiceImpl;
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    @Autowired
    public EmailService(VerificationTokenServiceImpl verificationTokenServiceImpl, TemplateEngine templateEngine,JavaMailSender javaMailSender){
    this.verificationTokenServiceImpl=verificationTokenServiceImpl;
    this.templateEngine=templateEngine;
    this.javaMailSender= javaMailSender;
    }
    public void sentHtmlMail(Seller seller) throws MessagingException{
        
        VerificationToken verificationToken=verificationTokenServiceImpl.findBySeller(seller);
        if(verificationToken!=null){
        
            String token=verificationToken.getToken();
            Context context=new Context();
            context.setVariable("title","Verify your email address");
            context.setVariable("link","http://localhost:8080/activation?token"+token);
            String body =templateEngine.process("verificationTemplate", context);
       
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setTo(seller.getEmail());
            helper.setSubject("Email address verification");
            helper.setText(body,true);
            javaMailSender.send(message);
        }   
        
    }
            
    }
    

