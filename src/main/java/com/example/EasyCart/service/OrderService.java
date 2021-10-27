/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service;

import com.example.EasyCart.dto.Checkout.CheckoutItemDto;
import com.example.EasyCart.dto.Order.PlaceOrderDto;
import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Order;
import com.example.EasyCart.exception.NotFoundException;
import com.example.EasyCart.exception.OrderNotFoundException;
import com.example.EasyCart.form.OrderForm;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface OrderService {

    Order saveOrder(PlaceOrderDto orderDto,  Customer customer);

//Order getOrderFromDto(PlaceOrderDto orderDto, Customer customer);
    List<Order> listOrders(Customer customer);
    
    List<Order>list();

    Order getOrder(int orderId) throws OrderNotFoundException;

//    public void placeOrder(Customer customer);
    public Order placeOrder(PlaceOrderDto placeOrderDto,Customer customer);
    
    void delete(Integer orderId) throws NotFoundException;
    
//    SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDto checkoutItemDto);
//
//    SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDto checkoutItemDto);
//    
//    public Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException;
}
