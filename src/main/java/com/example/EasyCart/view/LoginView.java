/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.view;

/**
 *
 * @author acer
 */
import com.example.EasyCart.entity.Admin;
import com.example.EasyCart.json.Json;
import com.example.EasyCart.security.util.TokenGenerator.Token;
import java.util.Date;

/**
 *
 * @author nirmal
 */
public class LoginView extends AdminView{

    public static class TokenView {

        private final String value;
        @Json.DateTimeFormat
        private final Date expiry;

        public TokenView(Token token) {
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

public LoginView(Admin admin, Token accessToken, Token refreshToken) {
        super(admin);
        this.accessToken = new TokenView(accessToken);
        this.refreshToken = new TokenView(refreshToken);
    }

    public LoginView(Admin admin, TokenView accessToken, TokenView refreshToken) {
        super(admin);
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public TokenView getAccessToken() {
        return accessToken;
    }
}
