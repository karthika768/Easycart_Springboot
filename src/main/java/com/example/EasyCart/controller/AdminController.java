/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.controller;

/**
 *
 * @author acer
 */

import com.example.EasyCart.entity.Admin;
import com.example.EasyCart.entity.Inbox;
import com.example.EasyCart.form.AdminForm;
import com.example.EasyCart.service.AdminService;



import com.example.EasyCart.view.AdminView;
import java.util.Collection;
import java.util.List;
import javax.mail.Message;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author karthika
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
//@CrossOrigin(origins="*")
@RequestMapping("/admin")


public class AdminController {

    @Autowired
    private AdminService adminService;
    

    

    @PostMapping
    public AdminView add(@Valid @RequestBody AdminForm form) {
        return adminService.add(form);
    }
    
     @GetMapping
    public Collection<Admin> list() {
        return adminService.list();
    }
   @GetMapping("/inbox")
   public Inbox receiveEmail(String host,String mailStroreType,String username,String password){
//     host="smtp.gmail.com";
//        mailStroreType="pop3s";
//        username="aeasy462@gmail.com";
//        password="zdhzjzqornuqnmjp";
        
        
        System.out.println("Suceess");
        return adminService.receiveEmail("smtp.gmail.com", "pop3s", "aeasy462@gmail.com","zdhzjzqornuqnmjp");
    }
    
    
//    @GetMapping("/inbox")
//    public EmailConfig index(){
//        return emailConfig;
//    }
    
//    @GetMapping("/inbox")
//    public InboxServiceImpl index(){
//       
//        return inboxServiceImpl;
//        
//        
//    }
    
}
