/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service.impl;

import com.example.EasyCart.entity.Admin;
import com.example.EasyCart.entity.Inbox;
import com.example.EasyCart.exception.BadRequestException;
import com.example.EasyCart.exception.NotFoundException;
import com.example.EasyCart.form.AdminForm;
import com.example.EasyCart.form.LoginForm;
import com.example.EasyCart.repository.AdminRepository;
import static com.example.EasyCart.security.AccessTokenAdminDetailsService.PURPOSE_ACCESS_TOKEN;
import com.example.EasyCart.security.config.SecurityConfig;
import com.example.EasyCart.security.util.InvalidTokenException;
import com.example.EasyCart.security.util.SecurityUtil;
import com.example.EasyCart.security.util.TokenExpiredException;
import com.example.EasyCart.security.util.TokenGenerator;
import com.example.EasyCart.security.util.TokenGenerator.Status;
import com.example.EasyCart.security.util.TokenGenerator.Token;
import com.example.EasyCart.service.AdminService;
import com.example.EasyCart.view.AdminView;
import com.example.EasyCart.view.LoginView;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

/**
 *
 * @author nirmal
 */
@Service
public class AdminServiceImpl implements AdminService {

    private static final String PURPOSE_REFRESH_TOKEN = "REFRESH_TOKEN";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private SecurityConfig securityConfig;

//    @Autowired
//    private MessageParser messageParser;
    @Override
    public AdminView add(AdminForm form) {
        return new AdminView(adminRepository.save(new Admin(
                form.getName(),
                form.getEmail(),
                passwordEncoder.encode(form.getPassword())
        )));
    }

    @Override
    public AdminView currentUser() {
        return new AdminView(
                adminRepository.findById(SecurityUtil.getCurrentAdminId()).orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public LoginView login(LoginForm form, Errors errors) throws BadRequestException {
        if (errors.hasErrors()) {
            throw badRequestException();
        }
        Admin admin = adminRepository.findByEmail(form.getEmail()).orElseThrow(AdminServiceImpl::badRequestException);
        if (!passwordEncoder.matches(form.getPassword(), admin.getPassword())) {
            throw badRequestException();
        }

        String id = String.format("%010d", admin.getAdminId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        Token refreshToken = tokenGenerator.create(PURPOSE_REFRESH_TOKEN, id + admin.getPassword(), securityConfig.getRefreshTokenExpiry());
        return new LoginView(admin, accessToken, refreshToken);
    }

    @Override
    public LoginView refresh(String refreshToken) throws BadRequestException {
        Status status;
        try {
            status = tokenGenerator.verify(PURPOSE_REFRESH_TOKEN, refreshToken);
        } catch (InvalidTokenException e) {
            throw new BadRequestException("Invalid token", e);
        } catch (TokenExpiredException e) {
            throw new BadRequestException("Token expired", e);
        }

        int adminId;
        try {
            adminId = Integer.parseInt(status.data.substring(0, 10));
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid token", e);
        }

        String password = status.data.substring(10);

        Admin admin = adminRepository.findByAdminIdAndPassword(adminId, password).orElseThrow(AdminServiceImpl::badRequestException);

        String id = String.format("%010d", admin.getAdminId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        return new LoginView(
                admin,
                new LoginView.TokenView(accessToken.value, accessToken.expiry),
                new LoginView.TokenView(refreshToken, status.expiry)
        );
    }

    private static BadRequestException badRequestException() {
        return new BadRequestException("Invalid credentials");
    }

    @Override
    public Collection<Admin> list() {
        return adminRepository.findAll();
    }

    @Override
    public AdminView get(Integer adminId) throws NotFoundException {
        return adminRepository.findById(adminId)
                .map((admin) -> {
                    return new AdminView(admin);
                }).orElseThrow(NotFoundException::new);
    }
//        @Transactional
//    @Override
//    public AdminView update(Integer adminId, AdminForm form) throws NotFoundException {
//        return adminRepository.findById(adminId)
//                .map((admin) -> {
//                    return new AdminView(adminRepository.save(admin.update(
//                            form.getName(),
//                            form.getEmail(),
//                           passwordEncoder.encode(form.getPassword())
//                   )));
//                }).orElseThrow(NotFoundException::new);
//    }

//    @Override
//    @Transactional
//     public void delete(Integer adminId) throws NotFoundException {
//         if((adminId)!=SecurityUtil.getCurrentAdminId()){
//         adminRepository.delete(
//                 adminRepository.findById(adminId)
//                       .orElseThrow(NotFoundException::new)
//        );
//         
//          
//
//         
//         }
//    }
    @Override
    public Inbox receiveEmail(String pop3Host, String storeType, String admin, String password) {
        String result = "";
        String subject = "";
        String from = "";
        Message message = null;
        Message[] messages = null;
        Inbox inbox = new Inbox();

        try {
            //get the session object

            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");
//            properties.put("pop.gmail.com", pop3Host);
//            properties.put("mail.pop3.port", "995");

            //get the session object
            Session session = Session.getInstance(properties, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("aeasy462@gmail.com", "zdhzjzqornuqnmjp"); //To change body of generated methods, choose Tools | Templates.
                }

            });
            //create the pop3 store object and connect with the pop server
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", "aeasy462@gmail.com", "zdhzjzqornuqnmjp");
            //create the folder object and open it
            Folder emailFolder = store.getFolder("inbox");
            emailFolder.open(Folder.READ_ONLY);

            //retrieve the messages from folder in an array
            messages = emailFolder.getMessages();
            for (int i = 0; i < messages.length; i++) {

                message = messages[i];

                System.out.println("______________________");
                System.out.println("Number of Email : " + (i + 1));
                System.out.println("Subject : " + message.getSubject());
                System.out.println("From : " + message.getFrom()[0]);
//                System.out.println("Text : " + message.getContent().toString());

                if (message.isMimeType("text/plain")) {
                    result = message.getContent().toString();
                } else if (message.isMimeType("multipart/*")) {
                    MimeMultipart mimeMultiPart = (MimeMultipart) message.getContent();
                    result = getTextFromMimeMutiPart(mimeMultiPart);
                }
                inbox.setSubject(message.getSubject());
                inbox.setSenderAddress(InternetAddress.toString(message.getFrom()));
                inbox.setContentType(result);
                System.out.println("_________________________");

            }

            // close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inbox;
//         return message.getContent();

    }

    public String getTextFromMimeMutiPart(MimeMultipart mimeMultiPart) throws MessagingException, IOException {
        String result = "";
        int count = mimeMultiPart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultiPart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break;
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + org.jsoup.Jsoup.parse(html).text();

            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result = result + getTextFromMimeMutiPart((MimeMultipart) bodyPart.getContent());

            }
        }
        System.out.println(result);
        return result;
    }

}
