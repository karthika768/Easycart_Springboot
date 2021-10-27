/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.dto.Order;

import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Order;
import com.example.EasyCart.form.OrderForm;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ACER
 */
public class PlaceOrderDto {

    private Integer placeOrderId;
    private @NotNull
    Customer customer;
    private @NotNull
    Double totalPrice;
    private @NotBlank
    String shippingAddress;
    private @NotBlank
    String paymentType;

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
      
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public PlaceOrderDto() {
    }

    public PlaceOrderDto(Order order) {
        this.setPlaceOrderId(order.getOrderId());
        this.setCustomer(order.getCustomer());
        this.setTotalPrice(order.getTotalPrice());
//        this.shippingAddress = order.getShippingAddress();
//        this.paymentType = order.getPaymentType();
    }

    public Integer getPlaceOrderId() {
        return placeOrderId;
    }

    public void setPlaceOrderId(Integer placeOrderId) {
        this.placeOrderId = placeOrderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
