/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service.impl;

import com.example.EasyCart.entity.SellerLogistics;
import com.example.EasyCart.exception.NotFoundException;
import com.example.EasyCart.form.SellerLogisticsForm;
import com.example.EasyCart.repository.SellerLogisticsRepository;
import com.example.EasyCart.service.SellerLogisticsService;
import com.example.EasyCart.view.SellerLogisticsDetailView;
import com.example.EasyCart.view.SellerLogisticsListView;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ACER
 */
@Service
public class SellerLogisticsServiceImpl implements SellerLogisticsService {

    @Autowired
    SellerLogisticsRepository sellerLogisticsRepository;

//   
     @Override
    public Collection<SellerLogistics> list() {
        return sellerLogisticsRepository.findAll();
    }

    @Override
    public SellerLogisticsDetailView add(SellerLogisticsForm form) {
        return new SellerLogisticsDetailView(sellerLogisticsRepository.save(new SellerLogistics(form)));
    }

    @Override
    public SellerLogisticsDetailView get(Integer logisticsId) throws NotFoundException {
       return sellerLogisticsRepository.findById(logisticsId)
                .map((sellerLogistics) -> {
                    return new SellerLogisticsDetailView(sellerLogistics);
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public SellerLogisticsDetailView update(Integer logisticsId, SellerLogisticsForm form) throws NotFoundException {
      return sellerLogisticsRepository.findById(logisticsId)
                .map((sellerLogistics) -> {
                    return new SellerLogisticsDetailView(sellerLogisticsRepository.save(sellerLogistics.update(form)));
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public void delete(Integer logisticsId) throws NotFoundException {
         sellerLogisticsRepository.delete(
               sellerLogisticsRepository.findById(logisticsId)
                        .orElseThrow(NotFoundException::new)
         );
    }

}
