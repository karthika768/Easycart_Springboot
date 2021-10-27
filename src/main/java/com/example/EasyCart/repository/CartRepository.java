/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.repository;

import com.example.EasyCart.entity.Cart;
import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.view.CartListView;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ACER
 */
@Repository

public interface CartRepository  extends JpaRepository<Cart, Integer>  {
    
     List<Cart> findAllByCustomerOrderByCreateDateDesc(Customer customer);

    List<Cart> deleteByCustomer(Customer customer);
    List<Cart> findProductById(int id);

    Optional<Cart> findByIdAndCustomerCustomerId(Integer id, Integer customerId);
    
    List<CartListView>findAllByCustomerCustomerId(Integer customerId);


}
