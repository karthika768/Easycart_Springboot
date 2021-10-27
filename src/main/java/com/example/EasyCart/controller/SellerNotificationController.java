/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.controller;

import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Order;
import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.entity.SellerNotification;
import com.example.EasyCart.repository.SellerNotificationRepository;
import com.example.EasyCart.service.CustomerService;
import com.example.EasyCart.service.OrderService;
import com.example.EasyCart.service.SellerNotificationService;
import com.example.EasyCart.service.SellerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins="*")
@RequestMapping("/seller/notification")
public class SellerNotificationController {

    @Autowired
    private SellerNotificationService sellerNotificationService;

    @Autowired
    private SellerNotificationRepository sellerNotificationRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

//    @Autowired
//    private SellerService sellerService;
    @PostMapping()
    public SellerNotification save(@RequestParam(value = "orderId", required = false) Integer orderId,
            @RequestParam(value = "message", required = false) String message) {
//        Order order = new Order();
//        Customer customer = customerService.currentCustomer();
//        String name = customer.getName();
//        System.out.println("Customer Name : "+ customer.toString());
        Order order = orderService.getOrder(orderId);
        SellerNotification sellerNotification = sellerNotificationService.send(order, message);
        return sellerNotification;
    }

    @GetMapping()
    public List<SellerNotification> get() {
        return sellerNotificationService.get();
    }

    @GetMapping("/customer")
    public List<SellerNotification> getByCustomer() {
        Customer customer = customerService.currentCustomer();
        return sellerNotificationService.getNotification(customer);
    }

//     @GetMapping("/seller")
//    public List<SellerNotification> getBySeller() {
//         Seller seller=sellerService.currentSeller;
//        return sellerNotificationService.getNotification(seller);
//    }
}
