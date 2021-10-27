/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.view;

import com.example.EasyCart.entity.Admin;
import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.json.Json;
import com.example.EasyCart.security.util.TokenGenerator;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class CustomerLoginView extends CustomerView{
    
      public static class TokenView {

        private final String value;
        @Json.DateTimeFormat
        private final Date expiry;

        public TokenView(TokenGenerator.Token token) {
            this.value = token.value;
            this.expiry = new Date(token.expiry);
        }

        public TokenView(String value, long expiry) {
            this.value = value;
            this.expiry = new Date(expiry);
        }

        public String getValue() {
            return value;
        }

        public Date getExpiry() {
            return expiry;
        }
    }

    private final TokenView accessToken;
    private final TokenView refreshToken;

public CustomerLoginView(Customer customer, TokenGenerator.Token accessToken, TokenGenerator.Token refreshToken) {
        super(customer);
        this.accessToken = new TokenView(accessToken);
        this.refreshToken = new TokenView(refreshToken);
    }

    public CustomerLoginView(Customer customer, TokenView accessToken, TokenView refreshToken) {
        super(customer);
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public TokenView getAccessToken() {
        return accessToken;
    }
    
}
