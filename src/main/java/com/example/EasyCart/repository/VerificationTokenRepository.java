/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.repository;

import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author arun
 */
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Integer> {
 
    VerificationToken findByToken(String token);
    VerificationToken findBySeller(Seller seller);
    
}
