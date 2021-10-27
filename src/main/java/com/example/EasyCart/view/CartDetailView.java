/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.view;

import com.example.EasyCart.entity.Cart;
import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Product;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class CartDetailView extends CartListView{
    
   
       
        
        
    public CartDetailView(Cart cart) {
         super(
                cart.getId(),
                cart.getCreateDate(),
                cart.getProduct(),
                 cart.getCustomer(),
                cart.getQuantity()
        );
    }
    
    
}
