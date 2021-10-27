/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.security.util;

import com.example.EasyCart.security.AccessTokenCustomerDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author ACER
 */
public final class CustomerSecurityUtil {

    public CustomerSecurityUtil() {
    }

    public static Integer getCurrentCustomerId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }

        Object principal = auth.getPrincipal();
        if (!(principal instanceof AccessTokenCustomerDetails)) {
//            System.out.println("Hi");
//             return ((AccessTokenCustomerDetails) principal).customerId;
            return null;

        } 
          return ((AccessTokenCustomerDetails) principal).customerId;

    }
}
