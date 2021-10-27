/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service.impl;

import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Order;
import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.entity.SellerNotification;
import com.example.EasyCart.repository.SellerNotificationRepository;
import com.example.EasyCart.security.util.CustomerSecurityUtil;
import com.example.EasyCart.service.CustomerService;
import com.example.EasyCart.service.SellerNotificationService;
import com.example.EasyCart.service.SellerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ACER
 */
@Service
public class SellerNotifcationServiceImpl implements SellerNotificationService {

    @Autowired
    private SellerNotificationRepository sellerNotificationRepository;
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private SellerService  sellerService;

    SellerNotification sellerNotification = new SellerNotification();
    
    
    @Override
    @Transactional
    public SellerNotification send(Order order, String message) {
//        Order order = new Order(orderId);
        // TODO Auto-generated method stub
//        sellerNotification.setOrder(order);
        
        return sellerNotificationRepository.save(new SellerNotification(message, order,order.getCustomer()));
    }

//	@Override
//	public Collection<Notification> get() {
//		// TODO Auto-generated method stub
//		return notificationRepository.findAll();
//	}
    @Override
    @Transactional
    public List<SellerNotification> get() {

        return sellerNotificationRepository.findAll();

    }

    @Override
    public List<SellerNotification> getNotification(Customer customer) {
        List<SellerNotification> notificationList = sellerNotificationRepository.findAllByCustomerOrderByCreateDateDesc(customerService.currentCustomer());
//        return sellerNotificationRepository.findAllByCreateDateDesc();
        return notificationList;

    }
    
//    @Override
//    public List<SellerNotification> getNotification(Seller seller) {
//        List<SellerNotification> notificationList = sellerNotificationRepository.findAllBySellerOrderByCreateDateDesc(sellerService.currentSeller());
////        return sellerNotificationRepository.findAllByCreateDateDesc();
//        return notificationList;
//
//    }

}
