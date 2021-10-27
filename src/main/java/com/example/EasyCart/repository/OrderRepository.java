/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.repository;

import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Order;
import com.example.EasyCart.view.CartListView;
//import com.example.EasyCart.view.OrderDetailView;
//import com.example.EasyCart.view.OrderListView;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ACER
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByCustomerOrderByCreateDateDesc(Customer customer);

    <Optional> Order findByOrderIdAndCustomerCustomerId(Integer orderId, Integer customerId);

//    Order save(Order order);
}
