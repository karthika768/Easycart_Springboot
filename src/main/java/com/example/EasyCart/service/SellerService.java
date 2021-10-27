/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service;

import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.exception.BadRequestException;
import com.example.EasyCart.exception.NotFoundException;
import com.example.EasyCart.form.LoginForm;
import com.example.EasyCart.form.SellerForm;
import com.example.EasyCart.view.LoginViewSeller;
import com.example.EasyCart.view.SellerView;
import java.util.Collection;
import org.springframework.validation.Errors;

/**
 *
 * @author arun
 */
public interface SellerService {
    
     Collection<Seller> list();
     
     Seller currentUser();

     SellerView add(SellerForm form);

     SellerView get(Integer sellerId) throws NotFoundException;

     SellerView update(Integer sellerId, SellerForm form) throws NotFoundException;

     void delete(Integer sellerId) throws NotFoundException;
      LoginViewSeller login(LoginForm form, Errors errors) throws BadRequestException;

      LoginViewSeller refresh(String refreshToken) throws BadRequestException;
        Seller save(Seller seller);
    
    
}
