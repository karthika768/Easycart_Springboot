/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.controller;

import com.example.EasyCart.Response.ApiResponse;
import com.example.EasyCart.dto.AddToCartDto;
import com.example.EasyCart.dto.CartDto;
import com.example.EasyCart.entity.Cart;
import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Product;
import com.example.EasyCart.exception.AuthenticationFailException;
import com.example.EasyCart.exception.CartItemNotExistException;
import com.example.EasyCart.exception.ProductNotExistException;
import com.example.EasyCart.repository.CartRepository;
import com.example.EasyCart.service.AdminService;
import com.example.EasyCart.service.CartService;
import com.example.EasyCart.service.CustomerService;
import com.example.EasyCart.service.ProductService;
import com.example.EasyCart.view.CartDetailView;
import com.example.EasyCart.view.CartListView;
import com.example.EasyCart.view.CustomerView;
import java.security.Principal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

/**
 *
 * @author ACER
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;
   
    
    @PostMapping("/add/{productId}")
    public ResponseEntity<ApiResponse> addToCart(@PathVariable("productId") int ID, @RequestBody AddToCartDto addToCartDto) throws AuthenticationFailException, ProductNotExistException {
        Customer customer;
        customer = customerService.currentCustomer();
        CustomerView customerView=customerService.getCustomer();
        System.out.println("customerView "+customerView.getName());
        System.out.println("Customer:"+customer);
        
        Product product = productService.findOne(ID);
//                getProductById(ID);
        System.out.println("product to add " + product.getProductName());
        cartService.addToCart(addToCartDto, product, customer);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
        
    }

    @GetMapping("/get")
    public ResponseEntity<CartDto> getCartItems() throws AuthenticationFailException {
        Customer customer = customerService.currentCustomer();
        
         System.out.println("Customer:"+customer);
        CartDto cartDto = cartService.listCartItems(customer);
        System.out.println("Cart Dto: "+cartDto);
       
//        return cartDto;
      return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
        
       
    }  
//    
//     @GetMapping("/getcart")
//    public ResponseEntity<CartDto> getCart() throws AuthenticationFailException {
//        Customer customer = customerService.currentCustomer();
//        
//         System.out.println("Customer:"+customer);
//        CartDto cartDto = cartService.listCartItems(customer);
//        System.out.println("Cart Dto: "+cartDto);
////        return cartDto;
//      return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
//        
//       
//    }  

    @GetMapping("/get/{cartItemId}")
    public ResponseEntity<CartDto> getCartItems(@PathVariable("cartItemId") int itemID) throws AuthenticationFailException {
        //   Customer customer = customerService.getCustomer();

        CartDto cartDto = cartService.listCartItems(itemID);
        return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
    }    
    
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @Valid AddToCartDto cartDto) throws AuthenticationFailException, ProductNotExistException {
        
        Customer customer = customerService.currentCustomer();
        
        Product product = productService.findOne(cartDto.getProductId());
        cartService.updateCartItem(cartDto, customer, product);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemID) throws AuthenticationFailException, CartItemNotExistException {
        int userId = customerService.currentCustomer().getCustomerId();
        cartService.deleteCartItem(itemID, userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }
    
//    @GetMapping()
//    public List<Cart> getCart() {
//        Customer customer = customerService.currentCustomer();
//        System.out.println(cartService.getCart(customer));
//        return  cartService.getCart(customer);
//    }
    
//   
    @GetMapping
    public List<CartListView> list(Principal p) {
        return cartService.list();
    }
    
}
