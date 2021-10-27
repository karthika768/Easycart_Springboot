/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.repository;

import com.example.EasyCart.entity.Seller;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.repository.Repository;

/**
 *
 * @author arun
 */
public interface SellerRepository extends Repository<Seller, Integer> {

    Optional<Seller> findById(Integer sellerId);

    Optional<Seller> findBySellerIdAndPassword(Integer sellerId, String password);

  Optional<Seller> findByEmail(String email);
   // Seller findByEmail(String email);

    Seller save(Seller seller);

    Collection<Seller> findAll();

    public void delete(Seller seller);

}
