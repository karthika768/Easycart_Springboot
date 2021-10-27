/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service.impl;

//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.checkout.Session;
//import com.stripe.param.checkout.SessionCreateParams;

import com.example.EasyCart.Enum.OrderStatusEnum;
import com.example.EasyCart.Enum.ResultEnum;
import com.example.EasyCart.Response.PurchaseResponse;
import com.example.EasyCart.dto.CartDto;
import com.example.EasyCart.dto.CartItemDto;
import com.example.EasyCart.dto.Checkout.CheckoutItemDto;
import com.example.EasyCart.dto.Order.PlaceOrderDto;
//import com.example.EasyCart.dto.OrderDto;
import com.example.EasyCart.entity.Cart;
import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Order;
import com.example.EasyCart.entity.OrderItem;
import com.example.EasyCart.exception.NotFoundException;
import com.example.EasyCart.exception.OrderNotFoundException;
import com.example.EasyCart.form.OrderForm;

import com.example.EasyCart.repository.OrderRepository;
import com.example.EasyCart.security.util.CustomerSecurityUtil;

import com.example.EasyCart.service.CartService;
import com.example.EasyCart.service.CustomerService;
import com.example.EasyCart.service.OrderItemService;
import com.example.EasyCart.service.OrderService;
import com.example.EasyCart.view.CartListView;
import java.util.ArrayList;
//import com.example.EasyCart.view.OrderDetailView;
//import com.example.EasyCart.view.OrderListView;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ACER
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    private CustomerService customerService;

//    @Value("${BASE_URL}")
    private String baseURL = "http://localhost:8080/";

//    @Value("${STRIPE_SECRET_KEY}")
//    private String apiKey;
    
    
    @Override
    public List<Order> list() {
          List<Order> orderList = orderRepository.findAll();
        return orderList;
      
    }
   
    @Override
    @Transactional
    public Order saveOrder(PlaceOrderDto orderDto,  Customer customer) {
        Order order = getOrderFromDto(orderDto,  customerService.currentCustomer());
//        System.out.println("Order : " + orderDto.getPlaceOrderId());

        return orderRepository.save(order);
    }

    @Transactional
    public Order getOrderFromDto(PlaceOrderDto orderDto, Customer customer) {
        Order order = new Order(orderDto,  customerService.currentCustomer());
        return order;
    }

    @Override
    @Transactional
    public List<Order> listOrders(Customer customer) {
        List<Order> orderList = orderRepository.findAllByCustomerOrderByCreateDateDesc(customerService.currentCustomer());
        return orderList;
    }

    @Override
    @Transactional
    public Order getOrder(int orderId) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        }
        throw new OrderNotFoundException("Order not found");
    }

    @Override
    @Transactional
//    public void placeOrder(Customer customer) {
    public Order placeOrder( PlaceOrderDto placeOrderDto , Customer customer) {
        CartDto cartDto = cartService.listCartItems(customerService.currentCustomer());

//        PlaceOrderDto placeOrderDto = new PlaceOrderDto();
        placeOrderDto.setCustomer(customerService.currentCustomer());
        System.out.println("Customer : "+customerService.currentCustomer());
        System.out.println();
        placeOrderDto.setTotalPrice(cartDto.getTotalCost());
        placeOrderDto.getShippingAddress();
        placeOrderDto.getPaymentType();
       
       
        

        Order newOrder = saveOrder(placeOrderDto, customer);
        List<CartItemDto> cartItemDtoList = cartDto.getcartItems();
        for (CartItemDto cartItemDto : cartItemDtoList) {
//            cartItemDtoList.stream().map(cartItemDto -> new OrderItem(

            OrderItem orderItem = new OrderItem(
                    newOrder,
                    cartItemDto.getProduct(),
                    cartItemDto.getQuantity(),
                    cartItemDto.getProduct().getMsrp());
            
            orderItemService.addOrderedProducts(orderItem);
        }
        cartService.deleteCustomerCartItems(customerService.currentCustomer());
        return newOrder;
    }

    
    
    @Override
    @Transactional
    public void delete(Integer orderId) throws NotFoundException {
        orderRepository.delete(
                orderRepository.findByOrderIdAndCustomerCustomerId(orderId, CustomerSecurityUtil.getCurrentCustomerId())
//                        .orElseThrow(NotFoundException::new)
        );
    }
    
    
    
//    @Override
//    public SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDto checkoutItemDto) {
//        return SessionCreateParams.LineItem.PriceData.builder()
//                .setCurrency("inr")
//                .setUnitAmount(((long) checkoutItemDto.getPrice()) * 100)
//                .setProductData(
//                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
//                                .setName(checkoutItemDto.getProductName())
//                                .build())
//                .build();
//    }
//
//    @Override
//    public SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDto checkoutItemDto) {
//        return SessionCreateParams.LineItem.builder()
//                .setPriceData(createPriceData(checkoutItemDto))
//                .setQuantity(Long.parseLong(String.valueOf(checkoutItemDto.getQuantity())))
//                .build();
//    }
//
//    @Override
//    public Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
//
//        String successURL = baseURL + "payment/success";
//        String failedURL = baseURL + "payment/failed";
//
////        Stripe.apiKey = apiKey;
//        List<SessionCreateParams.LineItem> sessionItemsList = new ArrayList<SessionCreateParams.LineItem>();
//        for (CheckoutItemDto checkoutItemDto : checkoutItemDtoList) {
//            sessionItemsList.add(createSessionLineItem(checkoutItemDto));
//        }
//
//        SessionCreateParams params = SessionCreateParams.builder()
//                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
//                .setMode(SessionCreateParams.Mode.PAYMENT)
//                .setCancelUrl(failedURL)
//                .addAllLineItem(sessionItemsList)
//                .setSuccessUrl(successURL)
//                .build();
//        return Session.create(params);
//    }

    
}
