/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.controller;

import com.example.EasyCart.form.AdminForm;
import com.example.EasyCart.form.LoginForm;
import com.example.EasyCart.service.AdminService;
import com.example.EasyCart.view.AdminView;
import com.example.EasyCart.view.LoginView;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/login")
@CrossOrigin(origins="http://localhost:4200")
//@CrossOrigin(origins="*")
public class LoginController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public AdminView currentUser() {
        return adminService.currentUser();
    }

    @PostMapping
    public LoginView login(@Valid @RequestBody LoginForm form, Errors errors) {
        return adminService.login(form, errors);
    }

    @PutMapping
    public LoginView refresh(@RequestBody String refreshToken) {
        return adminService.refresh(refreshToken);
    }
}
