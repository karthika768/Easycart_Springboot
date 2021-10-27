/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.repository;

import com.example.EasyCart.entity.OrderItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ACER
 */
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{
    
    Optional<OrderItem>findByOrderItemId(Integer orderItemId);
    
}
