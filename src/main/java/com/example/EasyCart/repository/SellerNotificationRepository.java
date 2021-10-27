/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.repository;

import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Order;
import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.entity.SellerNotification;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ACER
 */
public interface SellerNotificationRepository extends JpaRepository<SellerNotification,Integer>{
    
    
    SellerNotification save(String message);

	SellerNotification save(SellerNotification SellerNotification);
	
//Collection<Notification> findAllByCreateDateDesc();
//	Collection<SellerNotification> findAll();
//        List<SellerNotification> findAllByCreateDateDesc();
        
        List<SellerNotification> findAll();
        
        List<SellerNotification> findAllByCustomerOrderByCreateDateDesc(Customer customer);
        
         List<SellerNotification> findAllBySellerOrderByCreateDateDesc(Seller seller);
    
}
