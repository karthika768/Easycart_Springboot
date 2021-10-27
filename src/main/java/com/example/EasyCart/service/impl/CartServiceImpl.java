/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service.impl;

import com.example.EasyCart.dto.AddToCartDto;
import com.example.EasyCart.dto.CartDto;
import com.example.EasyCart.dto.CartItemDto;
//import com.example.EasyCart.dto.OrderDto;
import com.example.EasyCart.entity.Cart;
import com.example.EasyCart.entity.Customer;
//import com.example.EasyCart.entity.Order;
import com.example.EasyCart.entity.Product;
import com.example.EasyCart.exception.CartItemNotExistException;
import com.example.EasyCart.exception.NotFoundException;
//import com.example.EasyCart.repository.CartDtoRepository;
import com.example.EasyCart.repository.CartRepository;
import com.example.EasyCart.security.util.CustomerSecurityUtil;
//simport com.example.EasyCart.service.CartDtoService;
//import com.example.EasyCart.repository.OrderRepository;
import com.example.EasyCart.service.CartService;
import com.example.EasyCart.view.CartDetailView;
import com.example.EasyCart.view.CartListView;
import com.example.EasyCart.view.CustomerView;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author ACER
 */
@Service


public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;


    @Override
    @Transactional
    public void addToCart(AddToCartDto addToCartDto, Product product, Customer customer) {
        Cart cart = new Cart(product, addToCartDto.getQuantity(), customer);
        cartRepository.save(cart);
    }


    @Override
    public List<CartListView> list() {
        return cartRepository.findAllByCustomerCustomerId(CustomerSecurityUtil.getCurrentCustomerId());
    }


    @Override
    @Transactional
    public CartDto listCartItems(Customer customer) {
        List<Cart> cartList = cartRepository.findAllByCustomerOrderByCreateDateDesc(customer);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart : cartList) {
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto : cartItems) {
            totalCost += (cartItemDto.getProduct().getMsrp() * cartItemDto.getQuantity());
        }
        CartDto cartDto = new CartDto(cartItems, totalCost);
        
        return cartDto;
    }

    
    @Transactional

    public CartItemDto getDtoFromCart(Cart cart) {
        CartItemDto cartItemDto = new CartItemDto(cart);
        return cartItemDto;
    }

    
    @Override
    @Transactional
    public void updateCartItem(AddToCartDto cartDto, Customer customer, Product product) {
        Cart cart = cartRepository.getOne(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreateDate(new Date());
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void deleteCartItem(int productId, int customerId) throws CartItemNotExistException {
        if (!cartRepository.existsById(productId)) {
            throw new CartItemNotExistException("Cart id is invalid : " + productId);
        }
        cartRepository.deleteById(productId);
    }

    @Override
    @Transactional
    public void deleteCartItems(int customerId) {
        cartRepository.deleteAll();

    }

    @Override
    @Transactional
    public void deleteCustomerCartItems(Customer customer) {
        cartRepository.deleteByCustomer(customer);

    }

    @Override
    @Transactional
    public CartDto listCartItems(int productId) {
        List<Cart> cartList = cartRepository.findProductById(productId);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart : cartList) {
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }

        CartDto cartDto = new CartDto(cartItems);
        return cartDto;
    }


}
