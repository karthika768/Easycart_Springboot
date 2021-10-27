/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.controller;

import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.form.CustomerForm;
import com.example.EasyCart.form.CustomerLoginForm;
import com.example.EasyCart.form.LoginForm;
import com.example.EasyCart.service.CustomerService;
import com.example.EasyCart.view.CustomerLoginView;
import com.example.EasyCart.view.CustomerView;
import java.util.Collection;
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
 * @author ACER
 */

@RestController
@CrossOrigin(origins="http://localhost:4200")
//@CrossOrigin(origins="*")
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    
    
    @PostMapping
    public CustomerView add(@Valid @RequestBody CustomerForm form) {
        return customerService.add(form);
    }
    
     @GetMapping
    public Collection<Customer> list() {
        return customerService.list();
    }
    
//     @Autowired
//    private AdminService adminService;

    @GetMapping("/login")
    public CustomerView currentCustomer() {
        return customerService.getCustomer();
    }

    @PostMapping("/login")
    public CustomerLoginView login(@Valid @RequestBody CustomerLoginForm form, Errors errors) {
        return customerService.login(form, errors);
    }

    @PutMapping("/login")
    public CustomerLoginView refresh(@RequestBody String refreshToken) {
        return customerService.refresh(refreshToken);
    }
    
}
