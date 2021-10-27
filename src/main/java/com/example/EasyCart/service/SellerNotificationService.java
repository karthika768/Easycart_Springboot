/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service;

import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Order;
import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.entity.SellerNotification;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface SellerNotificationService {
    
    
    SellerNotification send(Order order,String message);
	
//	Collection<SellerNotification> get();
    
    List<SellerNotification> get();
    
//    List<SellerNotification> getNotification(Seller seller);
    
     List<SellerNotification> getNotification(Customer customer);
}
