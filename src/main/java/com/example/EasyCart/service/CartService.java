/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service;

import com.example.EasyCart.dto.AddToCartDto;
import com.example.EasyCart.dto.CartDto;
import com.example.EasyCart.dto.CartItemDto;
//import com.example.EasyCart.dto.OrderDto;
import com.example.EasyCart.entity.Cart;
import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Product;
import com.example.EasyCart.exception.CartItemNotExistException;
import com.example.EasyCart.view.CartDetailView;
import com.example.EasyCart.view.CartListView;
import com.example.EasyCart.view.CustomerView;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface CartService {

    public void addToCart(AddToCartDto addToCartDto, Product product, Customer customer);

    public CartDto listCartItems(Customer customer);
    
//    public CartDto listOfCartItems(Customer customer);
//    
//     public CartItemDto DtoFromCart(CartListView cart);

    public CartItemDto getDtoFromCart(Cart cart);

    public void updateCartItem(AddToCartDto cartDto, Customer customer, Product product);

    public void deleteCartItem(int productId, int customerId) throws CartItemNotExistException;

    public void deleteCartItems(int customerId);

    public void deleteCustomerCartItems(Customer customer);

    public CartDto listCartItems(int productId);
    
//     List<Cart> getCart(Customer customer);

    List<CartListView>list();
//     public CartDetailView get(Integer cartId);
//    void checkout(OrderDto orderDto);
}
