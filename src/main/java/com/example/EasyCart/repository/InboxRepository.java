/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.repository;

import com.example.EasyCart.entity.Admin;
import com.example.EasyCart.entity.Inbox;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.repository.Repository;

/**
 *
 * @author ACER
 */
public interface InboxRepository extends Repository<Inbox, Integer> {
    Optional<Inbox> findById(Integer inboxId);
      Inbox save(Inbox inbox);
    
    Collection<Inbox> findAll();
    
    public void deleteAll();
}
