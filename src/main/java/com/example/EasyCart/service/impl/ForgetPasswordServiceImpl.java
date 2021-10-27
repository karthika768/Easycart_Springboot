/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service.impl;

import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.exception.NotFoundException;
import com.example.EasyCart.repository.ForgetPasswordRepository;
import com.example.EasyCart.service.ForgetPasswordService;
//import com.example.EasyCart.service.ForgetPasswordService;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author acer
 */
@Service
public class ForgetPasswordServiceImpl implements ForgetPasswordService{
    
     @Autowired
    private ForgetPasswordRepository forgetPasswordRepository;
//  @Override
//    public void generateOtp(Integer otp) throws NotFoundException {
//         //generating OTP
//      
//        Random random=new Random(100000);
//        
//         otp = random.nextInt(999999);
//         System.out.println("OTP : "+otp);
//    }
    @Override
 public Seller save(Seller seller){
    
    return forgetPasswordRepository.save(seller);
            
            }   
}
