/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.repository;

import com.example.EasyCart.entity.Customer;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

/**
 *
 * @author ACER
 */
public interface CustomerRepository extends Repository<Customer, Integer> {

   Optional<Customer> findByCustomerId(Integer customerId);

    Optional<Customer> findByEmail(String email);
    
    Optional<Customer> findByCustomerIdAndPassword(Integer customerId, String password);

   Collection<Customer> findAll();

    Customer save(Customer customer);

    public void delete(Customer customer);

}
