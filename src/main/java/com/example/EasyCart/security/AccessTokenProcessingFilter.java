/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.security;

import static com.example.EasyCart.security.AccessTokenCustomerDetailService.PURPOSE_ACCESS_TOKEN;
import static com.example.EasyCart.security.AccessTokenAdminDetailsService.PURPOSE_ACCESS_TOKEN;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 *
 * @author nirmal
 */
public class AccessTokenProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

    private static final Pattern AUTH_PATTERN = Pattern.compile("Easycart ([0-9a-f]+)");

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        String authHeader = request.getHeader("authorization");
        if (authHeader == null) {
            return null;
        }

        Matcher matcher = AUTH_PATTERN.matcher(authHeader);
        if (!matcher.matches()) {
            return null;
        }

        return matcher.group(1);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return AccessTokenAdminDetailsService.PURPOSE_ACCESS_TOKEN;
    }
    
    
//     @Override
    protected Object getCustomerPreAuthenticatedCredentials(HttpServletRequest request) {
        return AccessTokenCustomerDetailService.PURPOSE_ACCESS_TOKEN;
    }
}
