/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.dto.Order;

import com.example.EasyCart.entity.Order;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ACER
 */
public class OrderDto {
    
    private Integer orderDtoId;
    private @NotNull Integer customerId;

    public OrderDto() {
    }

    public OrderDto(Order order) {
        this.setOrderDtoId(order.getOrderId());
//        this.setCustomerId(order.getCustomer());
    }

    public Integer getOrderDtoId() {
        return orderDtoId;
    }

    public void setOrderDtoId(Integer orderDtoId) {
        this.orderDtoId = orderDtoId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    
    
}
