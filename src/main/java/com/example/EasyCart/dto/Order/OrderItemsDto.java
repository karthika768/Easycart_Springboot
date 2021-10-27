/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.dto.Order;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ACER
 */
public class OrderItemsDto {

    private @NotNull
    Double price;
    private @NotNull
    int quantity;
    private @NotNull
    int orderId;
    private @NotNull
    int productId;
    private @NotNull
    int customerId;
    private @NotBlank
    String shippingAddress;
    private @NotBlank
    String paymentType;

    public OrderItemsDto() {
    }

    public OrderItemsDto(@NotNull Double price, @NotNull int quantity, @NotNull int orderId, @NotNull int productId, @NotNull int customerId, @NotBlank String shippingAddress, @NotBlank String paymentType) {
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
        this.customerId = customerId;
        this.shippingAddress = shippingAddress;
        this.paymentType = paymentType;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

}
