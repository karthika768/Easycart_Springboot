/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service.impl;

import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.exception.BadRequestException;
import com.example.EasyCart.exception.NotFoundException;
import com.example.EasyCart.form.LoginForm;
import com.example.EasyCart.form.SellerForm;
import com.example.EasyCart.repository.SellerRepository;
import static com.example.EasyCart.security.AccessTokenAdminDetailsService.PURPOSE_ACCESS_TOKEN;
import com.example.EasyCart.security.config.SecurityConfig;
import com.example.EasyCart.security.util.InvalidTokenException;
import com.example.EasyCart.security.util.SecurityUtil;
import com.example.EasyCart.security.util.TokenExpiredException;
import com.example.EasyCart.security.util.TokenGenerator;
import com.example.EasyCart.security.util.TokenGenerator.Status;
import com.example.EasyCart.security.util.TokenGenerator.Token;
import com.example.EasyCart.service.SellerService;
import com.example.EasyCart.view.LoginViewSeller;
import com.example.EasyCart.view.SellerView;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

/**
 *
 * @author arun
 */
@Service
public class SellerServiceImpl implements SellerService{
       private static final String PURPOSE_REFRESH_TOKEN = "REFRESH_TOKEN";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private SecurityConfig securityConfig;

     @Autowired
    private SellerRepository sellerRepository;
     
        @Autowired
    private VerificationTokenServiceImpl verificationTokenServiceImpl;
        @Autowired
    private EmailService emailService;
    
    
    @Override
    public SellerView add(SellerForm form) {
        Seller seller=new Seller(
                 form.getName(),
                 form.getcName(),
                 form.getAddress(),
                 form.getCity(),
                 form.getState(),
                 form.getCountry(),
                 form.getZipCode(),
                 form.getPh(),
                 form.getEmail(),
                 passwordEncoder.encode(form.getPassword()),
                 form.getLogo(),
                 form.getFileName()
        );
        Optional<Seller> saved=Optional.of(save(seller));
        saved.ifPresent(u ->{
        try{
            String token=UUID.randomUUID().toString();
            verificationTokenServiceImpl.save(saved.get(), token);
           emailService.sentHtmlMail(u);
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        });
        return new SellerView(saved.get());
    }

    @Override
    public Seller currentUser() {
        return new Seller(
                sellerRepository.findById(SecurityUtil.getCurrentAdminId()).orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public LoginViewSeller login(LoginForm form, Errors errors) throws BadRequestException {
        if (errors.hasErrors()) {
            throw badRequestException();
        }
        Seller seller = sellerRepository.findByEmail(form.getEmail()).orElseThrow(SellerServiceImpl::badRequestException);
        
        if (!passwordEncoder.matches(form.getPassword(), seller.getPassword())) {
            throw badRequestException();
        }

        String id = String.format("%010d", seller.getSellerId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        Token refreshToken = tokenGenerator.create(PURPOSE_REFRESH_TOKEN, id + seller.getPassword(), securityConfig.getRefreshTokenExpiry());
        return new LoginViewSeller(seller, accessToken, refreshToken);
    }

    @Override
    public LoginViewSeller refresh(String refreshToken) throws BadRequestException {
        Status status;
        try {
            status = tokenGenerator.verify(PURPOSE_REFRESH_TOKEN, refreshToken);
        } catch (InvalidTokenException e) {
            throw new BadRequestException("Invalid token", e);
        } catch (TokenExpiredException e) {
            throw new BadRequestException("Token expired", e);
        }

        int sellerId;
        try {
            sellerId = Integer.parseInt(status.data.substring(0, 10));
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid token", e);
        }

        String password = status.data.substring(10);

       Seller seller = sellerRepository.findBySellerIdAndPassword(sellerId, password).orElseThrow(SellerServiceImpl::badRequestException);

        String id = String.format("%010d", seller.getSellerId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        return new LoginViewSeller(
                seller,
                new LoginViewSeller.TokenView(accessToken.value, accessToken.expiry),
                new LoginViewSeller.TokenView(refreshToken, status.expiry)
        );
    }

    private static BadRequestException badRequestException() {
        return new BadRequestException("Invalid credentials");
    }

  

    @Override
    public SellerView get(Integer sellerId) throws NotFoundException {
        return sellerRepository.findById(sellerId)
                .map((seller) -> {
                    return new SellerView(seller);
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    public SellerView update(Integer sellerId, SellerForm form) throws NotFoundException {
        return sellerRepository.findById(sellerId)
                .map((seller) -> {
                    return new SellerView(sellerRepository.save(seller.update(form)));
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(Integer sellerId) throws NotFoundException {
        sellerRepository.delete(
                sellerRepository.findById(sellerId)
                        .orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public Collection<Seller> list() {
        return sellerRepository.findAll();
    }
    
    public Seller save(Seller seller){
    
    return sellerRepository.save(seller);
            
            }

    
    
    }

  
    

