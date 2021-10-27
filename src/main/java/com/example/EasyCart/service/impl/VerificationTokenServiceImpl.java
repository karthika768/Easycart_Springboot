/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service.impl;

import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.entity.VerificationToken;
import com.example.EasyCart.repository.VerificationTokenRepository;
import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author arun
 */
@Service
public class VerificationTokenServiceImpl {
     private final VerificationTokenRepository verificationTokenRepository;
   @Autowired
   public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository){
   this.verificationTokenRepository=verificationTokenRepository;
   
   }
   @Transactional
   public VerificationToken findByToken(String token){
   return verificationTokenRepository.findByToken(token);
   }
   
   @Transactional
   public VerificationToken findBySeller(Seller seller){
   return verificationTokenRepository.findBySeller(seller);
   }
   
   @Transactional
   public void save(Seller seller, String token){
       VerificationToken verificationToken=new VerificationToken(seller,token);
       verificationToken.setExpireDate(calculateExpireDate(24*60));
     verificationTokenRepository.save(verificationToken);
   }
    
   private Timestamp calculateExpireDate(int expireTimeInMinutes )
   {
   Calendar cal=Calendar.getInstance();
   cal.add(Calendar.MINUTE, expireTimeInMinutes);
   return new Timestamp(cal.getTime().getTime());
   }
   
}
