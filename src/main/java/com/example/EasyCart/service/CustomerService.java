/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service;

import com.example.EasyCart.exception.NotFoundException;
import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.exception.BadRequestException;
import com.example.EasyCart.form.CustomerForm;
import com.example.EasyCart.form.CustomerLoginForm;
import com.example.EasyCart.view.CustomerLoginView;
import com.example.EasyCart.view.CustomerView;
import java.util.Collection;
import org.springframework.validation.Errors;

/**
 *
 * @author ACER
 */
public interface CustomerService {

    CustomerView add(CustomerForm form);

//    Customer getCustomer();
    Collection<Customer> list();

    Customer currentCustomer();

    CustomerView getCustomer();

    CustomerLoginView login(CustomerLoginForm form, Errors errors) throws BadRequestException;

    CustomerLoginView refresh(String refreshToken) throws BadRequestException;

    CustomerView get(Integer customerId) throws NotFoundException;
//    <Optional> Customer getCustomer(Integer customerId) throws NotFoundException;

//    Customer findByCustomerId(Integer customerId);
//    Customer findOne(String email);
//    Collection<Customer> findByRole(String role);
    Customer save(Customer customer);

//    Customer update(Customer customer);
}
