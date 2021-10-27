/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.repository;

import com.example.EasyCart.entity.Admin;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.repository.Repository;

/**
 *
 * @author nirmal
 */
public interface AdminRepository extends Repository<Admin, Integer> {

    Optional<Admin> findById(Integer adminId);

    Optional<Admin> findByAdminIdAndPassword(Integer adminId, String password);

    
    
    Optional<Admin> findByEmail(String email);

    Admin save(Admin admin);
    
    Collection<Admin> findAll();
    
    public void delete(Admin admin);

   
}

