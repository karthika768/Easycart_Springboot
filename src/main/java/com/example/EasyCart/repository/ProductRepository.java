/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.repository;

import com.example.EasyCart.entity.Product;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ACER
 */
public interface ProductRepository extends JpaRepository<Product,Integer>{
    
     <Optional>Product findByProductId(Integer productId);
    // onsale product
  Page<Product> findAllByStatusOrderByProductIdAsc(Integer status, Pageable pageable);
//       Optional<Product> findAllByProductStatusOrderByProductIdAsc(byte status);
      

    // product in one category
//    Page<Product> findAllByCategoryTypeOrderByProductIdAsc(Integer categoryType, Pageable pageable);

  Page<Product> findAllByOrderByProductId(Pageable pageable);
//        Optional<Product> findAllByOrderByProductId();
  
   Page<Product> findByProductNameContaining(@Param("product_name") String productName, Pageable pageable);
    
}
