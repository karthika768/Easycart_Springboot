/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.repository;

import com.example.EasyCart.entity.Seller;
import org.springframework.data.repository.Repository;

/**
 *
 * @author acer
 */
public interface ForgetPasswordRepository extends Repository<Seller, Integer> {
   
    
    Seller findByEmail(String email);
    
    Seller save(Seller seller);
    
}
