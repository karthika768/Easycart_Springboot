/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service;

import com.example.EasyCart.entity.Product;
import com.example.EasyCart.exception.NotFoundException;
import com.example.EasyCart.view.CustomerView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author ACER
 */
public interface ProductService {

    
    
//  public Product getProductById(Integer productId)throws NotFoundException ;
    
    Product findOne(Integer productId);

    // All selling products
    Page<Product> findUpAll(Pageable pageable);

    // All products
    Page<Product> findAll(Pageable pageable);
    // All products in a category
//    Page<Product> findAllInCategory(Integer categoryType, Pageable pageable);

    // increase stock
    void increaseStock(Integer productId, int amount);

    //decrease stock
    void decreaseStock(Integer productId, int amount);

    Product offSale(Integer productId);

    Product onSale(Integer productId);

    Product update(Product productInfo);

    Product save(Product productInfo);

    void delete(Integer productId);

}
