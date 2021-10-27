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
//import com.example.EasyCart.entity.Order;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Date;
//
///**
// *
// * @author ACER
// */
//public class OrderDetailView extends OrderListView {
//
//    private final Collection<Cart> cartItems;
//
//    public OrderDetailView(Order order) {
//        super(
//                order.getOrderId(),
//                order.getOrderTrackingNumber(),
////                order.getTotalQuantity(),
////                order.getTotalPrice(),
//                order.getStatus(),
//                order.getCreateDate(),
//                order.getUpdateDate(),
////                order.getCustomer(),
//                order.getShippingAddress()
////                order.getBillingAddress()
//        );
//        this.cartItems = Collections.unmodifiableCollection(order.getCartItems());
//    }
//
//    public Collection<Cart> getCartItems() {
//        return cartItems;
//    }
//
//}
