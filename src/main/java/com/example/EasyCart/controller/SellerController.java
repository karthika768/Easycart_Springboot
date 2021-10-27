/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.controller;

import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.entity.VerificationToken;
import com.example.EasyCart.form.SellerForm;
import com.example.EasyCart.service.SellerService;
import com.example.EasyCart.service.impl.VerificationTokenServiceImpl;
import com.example.EasyCart.view.SellerView;
import java.sql.Timestamp;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author arun
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
//@CrossOrigin(origins="*")
@RequestMapping("/seller")
public class SellerController {
      
    private final SellerService sellerService;   
    private final VerificationTokenServiceImpl verificationTokenServiceImpl;
    private  Seller seller;
    
    @Autowired
    public SellerController(SellerService sellerService,VerificationTokenServiceImpl verificationTokenServiceImpl){
    this.sellerService=sellerService;
    this.verificationTokenServiceImpl=verificationTokenServiceImpl;
    
    
    }
    @PostMapping
    public SellerView add(@Valid @RequestBody SellerForm form) {
        return sellerService.add(form);
    }
    
     @GetMapping
    public Collection<Seller> list() {
        return sellerService.list();
    }
    @GetMapping("/activation")
    public String activation(@RequestParam("token") String token, Model model){
      
       VerificationToken verificationToken= verificationTokenServiceImpl.findByToken(token);
       if(verificationToken==null){
       model.addAttribute("message","Your verification token is invalid!");
       }
       else{
       seller=verificationToken.getSeller();
       if(!seller.isEnabled()){
       
           Timestamp currentTimestamp=new Timestamp(System.currentTimeMillis());
       
       if(verificationToken.getExpireDate().before(currentTimestamp)){
       model.addAttribute("message","Your verification token has expired");
       }
       else{
       seller.setEnabled(true);
       sellerService.save(seller);
       model.addAttribute("message", "Your account is successfully activated");
       }
       }
       else{
           model.addAttribute("message", "Your account is already activated");
               } 
       }
       
        
    return "activation";
    }
    
}
