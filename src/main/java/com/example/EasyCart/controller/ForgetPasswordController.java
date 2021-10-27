/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.controller;

import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.form.ForgetPasswordForm;
import com.example.EasyCart.form.NewPasswordForm;
import com.example.EasyCart.form.VerifyOtpForm;
import com.example.EasyCart.repository.SellerRepository;
import com.example.EasyCart.security.config.SecurityConfig;
import com.example.EasyCart.service.impl.ForgetEmailService;
import com.example.EasyCart.service.impl.SellerServiceImpl;
import java.util.Optional;
//import com.example.EasyCart.service.ForgetPasswordService;
import java.util.Random;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.EasyCart.repository.ForgetPasswordRepository;

/**
 *
 * @author acer
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
//@CrossOrigin(origins = "*")
@RequestMapping("/forgetPassword")
public class ForgetPasswordController {

//    @Autowired
//    private ForgetPasswordService forgetPasswordService;
    @Autowired
    private ForgetEmailService forgetEmailService;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private ForgetPasswordRepository forgetPasswordRepository;

    @PostMapping
    public String sendOtp(@Valid @RequestBody ForgetPasswordForm form, HttpSession session) {
        System.out.println("email : " + form.getEmail());
        Random random = new Random();

        int otp = random.ints(100000,999999).findFirst().getAsInt();
        System.out.println("OTP : " + otp);

        String subject = "Otp for Forget Password";
        String message = ""
                + "<div style='border:1px solid black;padding:20px;'>"
                + "<h2>"
                + "Otp for password reset is "
                + "<b>" + otp
                + "</b>"
                + "</h2>"
                + "</div>";
        String to = form.getEmail();

        //write the email
        boolean flag = this.forgetEmailService.sendMail(subject, message, to);

        if (flag) {
            session.setAttribute("myOtp", otp);
            session.setAttribute("email", form.getEmail());
            return "success";
        } else {
            session.setAttribute("message", "check your email id");
            return "failed";
        }
//         return "sucess";
        //return forgetPasswordService.generateOtp(otp);
    }

    @PostMapping("/verifyOtp")
    public String verifyOtp(@Valid @RequestBody VerifyOtpForm verifyForm, HttpSession session) {
        int myOtp = (int) session.getAttribute("myOtp");
        String email = (String) session.getAttribute("email");
        if (myOtp == verifyForm.getOtp()) {

            System.out.println("otps are equal");
            Seller seller = new Seller();
//            String s = seller.getEmail();
            System.out.println("Session Email: " + email);

            seller = this.forgetPasswordRepository.findByEmail(email);
            System.out.println("Seller : " + seller);
//            Seller seller = this.sellerRepository.findByUserName(email);
            if (seller == null) {

                session.setAttribute("message", "You are not a registered seller");
                System.out.println("Seller not found");
                return "seller not found";
            } else {
                //if otps are same and seller found we can change password
                return "you can change password";
            }

        } else {
            session.setAttribute("message", "You have entered wrong otp");
            System.out.println("incorrect otp");
            return "incorrect otp";

        }

    }

    @PostMapping("/changePassword")
//    @RequestParam("newPassword") String newPasssword
    public String changePassword(@Valid @RequestBody NewPasswordForm newPasswordForm, HttpSession session) {
        String email = (String) session.getAttribute("email");
        // Optional<Seller> seller = this.sellerRepository.findByEmail(email);
        Seller seller = this.forgetPasswordRepository.findByEmail(email);
        seller.setPassword(this.passwordEncoder.encode(newPasswordForm.getNewPassword()));
//        this.sellerRepository.save(seller);
        this.forgetPasswordRepository.save(seller);

        session.setAttribute("message", "Password Updated");
        System.out.println("Password Updated");
        return "Password Updated";
    }
}
