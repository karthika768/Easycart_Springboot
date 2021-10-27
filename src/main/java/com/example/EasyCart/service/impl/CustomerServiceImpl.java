/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service.impl;

//import com.example.EasyCart.entity.Cart;
import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.exception.BadRequestException;
import com.example.EasyCart.exception.NotFoundException;
import com.example.EasyCart.form.CustomerForm;
import com.example.EasyCart.form.CustomerLoginForm;
import com.example.EasyCart.form.LoginForm;

import com.example.EasyCart.repository.CustomerRepository;
import static com.example.EasyCart.security.AccessTokenAdminDetailsService.PURPOSE_ACCESS_TOKEN;
import com.example.EasyCart.security.config.SecurityConfig;
import com.example.EasyCart.security.util.CustomerSecurityUtil;
import com.example.EasyCart.security.util.InvalidTokenException;
import com.example.EasyCart.security.util.SecurityUtil;
import com.example.EasyCart.security.util.TokenExpiredException;
import com.example.EasyCart.security.util.TokenGenerator;
import com.example.EasyCart.security.util.TokenGenerator.Status;
import com.example.EasyCart.security.util.TokenGenerator.Token;

import com.example.EasyCart.service.CustomerService;
import com.example.EasyCart.view.CustomerLoginView;

import com.example.EasyCart.view.CustomerView;
import com.example.EasyCart.view.LoginViewSeller;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

/**
 *
 * @author ACER
 */
@Service
//@DependsOn("passwordEncoder")
public class CustomerServiceImpl implements CustomerService {

    private static final String PURPOSE_REFRESH_TOKEN = "REFRESH_TOKEN";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private SecurityConfig securityConfig;

//    @Override
//    public Customer findOne(String email) {
//        return customerRepository.findByEmail(email);
//    }
//    
    Integer customerId;

    @Override
    public CustomerView add(CustomerForm form) {
        return new CustomerView(customerRepository.save(new Customer(
                form.getName(),
                form.getEmail(),
                form.getPhone(),
                passwordEncoder.encode(form.getPassword())
        )));
    }
    
    
    @Override
    public CustomerView getCustomer() {
        return new CustomerView(
                customerRepository.findByCustomerId(CustomerSecurityUtil.getCurrentCustomerId()).orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public Customer currentCustomer() {
//        System.out.println(customerRepository.findById(CustomerSecurityUtil.getCurrentCustomerId()));
        return new Customer(
                customerRepository.findByCustomerId(CustomerSecurityUtil.getCurrentCustomerId()).orElseThrow(NotFoundException::new)
               
        );
        
    }
    

    @Override
    public CustomerLoginView login(CustomerLoginForm form, Errors errors) throws BadRequestException {
        if (errors.hasErrors()) {
            throw badRequestException();
        }
        Customer customer = customerRepository.findByEmail(form.getEmail()).orElseThrow(CustomerServiceImpl::badRequestException);
        if (!passwordEncoder.matches(form.getPassword(), customer.getPassword())) {
            throw badRequestException();
        }

        String id = String.format("%010d", customer.getCustomerId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        Token refreshToken = tokenGenerator.create(PURPOSE_REFRESH_TOKEN, id + customer.getPassword(), securityConfig.getRefreshTokenExpiry());
        return new CustomerLoginView(customer, accessToken, refreshToken);
    }

    @Override
    public CustomerLoginView refresh(String refreshToken) throws BadRequestException {
        Status status;
        try {
            status = tokenGenerator.verify(PURPOSE_REFRESH_TOKEN, refreshToken);
        } catch (InvalidTokenException e) {
            throw new BadRequestException("Invalid token", e);
        } catch (TokenExpiredException e) {
            throw new BadRequestException("Token expired", e);
        }

        int customerId;
        try {
            customerId = Integer.parseInt(status.data.substring(0, 10));
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid token", e);
        }

        String password = status.data.substring(10);

        Customer customer = customerRepository.findByCustomerIdAndPassword(customerId, password).orElseThrow(CustomerServiceImpl::badRequestException);

        String id = String.format("%010d", customer.getCustomerId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        return new CustomerLoginView(
                customer,
                new CustomerLoginView.TokenView(accessToken.value, accessToken.expiry),
                new CustomerLoginView.TokenView(refreshToken, status.expiry)
        );
    }

    private static BadRequestException badRequestException() {
        return new BadRequestException("Invalid credentials");
    }

    @Override
    public Collection<Customer> list() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerView get(Integer customerId) throws NotFoundException {
        return customerRepository.findByCustomerId(customerId)
                .map((customer) -> {
                    return new CustomerView(customer);
                }).orElseThrow(NotFoundException::new);
    }

//    @Override
//    public Customer getCustomer(Integer customerId) throws NotFoundException {
//        return customerRepository.findById(customerId)
//                .map((customer) -> {
//                    return new Customer(customer);
//                }).orElseThrow(NotFoundException::new);
//    }
    @Override
    @Transactional
    public Customer save(Customer customer) throws BadRequestException {
        //register
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        try {
            Customer savedUser = customerRepository.save(customer);

            // initial Cart
//            Cart savedCart = cartRepository.save(new Cart(savedUser));
//            savedUser.setCart(savedCart);
            return customerRepository.save(savedUser);

        } catch (Exception e) {
            throw new BadRequestException();
        }

    }

//    @Override
//    @Transactional
//    public Customer update(Customer customer) {
//        Customer oldUser = customerRepository.findByEmail(customer.getEmail());
//        oldUser.setPassword(passwordEncoder.encode(customer.getPassword()));
//        oldUser.setName(customer.getName());
//        oldUser.setPhone(customer.getPhone());
//        oldUser.setAddress(customer.getAddress());
//        return customerRepository.save(oldUser);
//    }
//    @Override
//    public Optional<Customer> getCustomer() {
//
//        return customerRepository.findById(CustomerSecurityUtil.getCurrentCustomerId());
//
//    }

}
