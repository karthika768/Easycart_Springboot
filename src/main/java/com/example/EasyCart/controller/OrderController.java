/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.controller;

import com.example.EasyCart.Response.ApiResponse;
import com.example.EasyCart.Response.PurchaseResponse;
import com.example.EasyCart.dto.CartDto;
import com.example.EasyCart.dto.Checkout.CheckoutItemDto;
import com.example.EasyCart.dto.Checkout.StripeResponse;
import com.example.EasyCart.dto.Order.PlaceOrderDto;
//import com.example.EasyCart.dto.OrderDto;
import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Order;
import com.example.EasyCart.entity.OrderItem;
import com.example.EasyCart.exception.AuthenticationFailException;
import com.example.EasyCart.exception.OrderNotFoundException;
import com.example.EasyCart.exception.ProductNotExistException;


import com.example.EasyCart.service.CustomerService;
import com.example.EasyCart.service.OrderItemService;
import com.example.EasyCart.service.OrderService;
//import com.stripe.exception.StripeException;
//import com.stripe.model.checkout.Session;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ACER
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins="*")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    
    @Autowired
    private OrderItemService orderItemService;

    
    @Autowired
    private CustomerService customerService;

//    @Autowired
//    private AuthenticationService authenticationService;


    @PostMapping("/add")
    public Order placeOrder(@RequestBody PlaceOrderDto placeOrderDto)
            throws ProductNotExistException, AuthenticationFailException {
        Customer customer=customerService.currentCustomer();
        Order order = orderService.placeOrder(placeOrderDto,customer);
        return order;
//        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Order has been placed"), HttpStatus.CREATED);
    }
    
    @GetMapping("/all")
    public List<Order> AllOrders() throws AuthenticationFailException {
        
      
        List<Order> orderDtoList = orderService.list();
        return orderDtoList;
//        return new ResponseEntity<List<Order>>(orderDtoList,HttpStatus.OK);
    }

    @GetMapping
    public List<Order> getAllOrders() throws AuthenticationFailException {
        
       Customer customer=customerService.currentCustomer();
        List<Order> orderDtoList = orderService.listOrders(customer);
        return orderDtoList;
//        return new ResponseEntity<List<Order>>(orderDtoList,HttpStatus.OK);
    }

//    @PostMapping("/create-checkout-session")
//    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
//        Session session = orderService.createSession(checkoutItemDtoList);
//        StripeResponse stripeResponse = new StripeResponse(session.getId());
//        return new ResponseEntity<StripeResponse>(stripeResponse,HttpStatus.OK);
//    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getAllOrders(@PathVariable("orderId") Integer orderId) throws AuthenticationFailException {
        
        try {
            Order order = orderService.getOrder(orderId);
            
            return new ResponseEntity<>(order,HttpStatus.OK);
        }
        catch (OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    
    
     @DeleteMapping("/{orderId}")
    public void delete(@PathVariable("orderId") Integer orderId) {
        orderService.delete(orderId);
    }
    
    
     @GetMapping("/orderlist/{orderItemId}")
    public ResponseEntity<Object> getAllOrderItems(@PathVariable("orderItemId") Integer orderItemId) throws AuthenticationFailException {
        
        try {
            OrderItem order = orderItemService.getOrderItem(orderItemId);
            
            return new ResponseEntity<>(order,HttpStatus.OK);
        }
        catch (OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    
     @DeleteMapping("/orderlist/{orderItemId}")
    public void deleteOrederItem(@PathVariable("orderItemId") Integer orderItemId) {
        orderItemService.delete(orderItemId);
    }
    
    
    
//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getAllOrders(@PathVariable("orderId") Integer orderId) throws AuthenticationFailException {
//        Customer customer=customerService.currentCustomer();
//        try {
//            Order order = orderService.getOrder(orderId);
//            return new ResponseEntity<>(order,HttpStatus.OK);
//        }
//        catch (OrderNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
//        }
//
//    }

}
