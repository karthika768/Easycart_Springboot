/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.dto;

import com.example.EasyCart.entity.Cart;
import com.example.EasyCart.entity.Product;
import com.example.EasyCart.view.CartListView;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ACER
 */
public class CartItemDto {
     private Integer id;
    private @NotNull Integer customerId;
    private @NotNull Integer quantity;
    private @NotNull Product product;
    public CartItemDto() {
    }
  public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setCustomerId(cart.getCustomer().getCustomerId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }
  
   public CartItemDto(CartListView cart) {
        this.setId(cart.getId());
        this.setCustomerId(cart.getCustomer().getCustomerId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }
  @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", quantity=" + quantity +
                ", productName=" + product.getProductName() +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}

