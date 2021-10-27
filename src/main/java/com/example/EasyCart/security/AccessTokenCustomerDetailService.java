/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.security;

import com.example.EasyCart.security.util.TokenGenerator;
import com.example.EasyCart.security.util.TokenGenerator.Status;
import com.example.EasyCart.security.util.InvalidTokenException;
import com.example.EasyCart.security.util.TokenExpiredException;
import com.example.EasyCart.security.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

/**
 *
 * @author ACER
 */
public class AccessTokenCustomerDetailService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken>{
    
    public static final String PURPOSE_ACCESS_TOKEN = "ACCESS_TOKEN";

    @Autowired
    private TokenGenerator tokenGenerator;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        if (!PURPOSE_ACCESS_TOKEN.equals(token.getCredentials())) {
            throw new UsernameNotFoundException("Invalid credentials");
        }

        final Status status;
        try {
            status = tokenGenerator.verify(PURPOSE_ACCESS_TOKEN, token.getPrincipal().toString());
        } catch (InvalidTokenException e) {
            throw new UsernameNotFoundException("Invalid access token", e);
        } catch (TokenExpiredException e) {
            throw new UsernameNotFoundException("Access token expired", e);
        }
        System.out.println( Integer.parseInt(status.data));
        return new AccessTokenCustomerDetails(Integer.parseInt(status.data));
    }
    
}
