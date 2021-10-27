///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.example.EasyCart.view;
//
//import com.example.EasyCart.entity.Address;
//import com.example.EasyCart.entity.Cart;
//import com.example.EasyCart.entity.Customer;
//import com.example.EasyCart.json.Json;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//
///**
// *
// * @author ACER
// */
//public class OrderListView {
//    
//     private final Integer orderId;
//    private  final String orderTrackingNumber;
//    
////   private final Integer totalQuantity;
////   
////    private final Double totalPrice;
//    private final byte status;
//
//  @Json.DateTimeFormat
//    private final Date createDate;
//    @Json.DateTimeFormat
//    private final Date updateDate;
//
//    
////    private final Customer customer;
//
//    
//    private final Address shippingAddress;
//
// 
////    private final Address billingAddress;
//
//    public Integer getOrderId() {
//        return orderId;
//    }
//
//    public String getOrderTrackingNumber() {
//        return orderTrackingNumber;
//    }
//
////    public Integer getTotalQuantity() {
////        return totalQuantity;
////    }
////
////    public Double getTotalPrice() {
////        return totalPrice;
////    }
//
//    public byte getStatus() {
//        return status;
//    }
//
//    public Date getCreateDate() {
//        return createDate;
//    }
//
//    public Date getUpdateDate() {
//        return updateDate;
//    }
////
////    public Customer getCustomer() {
////        return customer;
////    }
//
//    public Address getShippingAddress() {
//        return shippingAddress;
//    }
//
////    public Address getBillingAddress() {
////        return billingAddress;
////    }
//
//  
//    private final Collection<Cart> cartItems = new ArrayList<>();
//
//    public OrderListView(Integer orderId, String orderTrackingNumber, byte status, Date createDate, Date updateDate, Address shippingAddress) {
//        this.orderId = orderId;
//        this.orderTrackingNumber = orderTrackingNumber;
////        this.totalQuantity = totalQuantity;
////        this.totalPrice = totalPrice;
//        this.status = status;
//        this.createDate = createDate;
//        this.updateDate = updateDate;
////        this.customer = customer;
//        this.shippingAddress = shippingAddress;
////        this.billingAddress = billingAddress;
//    }
//
//    
//}
