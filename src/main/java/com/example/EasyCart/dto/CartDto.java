/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.dto;

import java.util.List;

/**
 *
 * @author ACER
 */
public class CartDto {
        private List<CartItemDto> cartItems;
          private Double totalCost;

    public CartDto(List<CartItemDto> cartItemDtoList, Double totalCost) {
        this.cartItems = cartItemDtoList;
        this.totalCost = totalCost;
    }
    public CartDto(List<CartItemDto> cartItemDtoList) {
        this.cartItems = cartItemDtoList;
    }

    public CartDto() {
       
    }

    public List<CartItemDto> getcartItems() {
        
        return cartItems;
    }
    

    public void setCartItems(List<CartItemDto> cartItemDtoList) {
        this.cartItems = cartItemDtoList;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
   
}
