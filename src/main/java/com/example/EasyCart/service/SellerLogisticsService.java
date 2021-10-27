/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service;

import com.example.EasyCart.entity.SellerLogistics;
import com.example.EasyCart.exception.NotFoundException;
import com.example.EasyCart.form.SellerLogisticsForm;
import com.example.EasyCart.view.SellerLogisticsDetailView;
import com.example.EasyCart.view.SellerLogisticsListView;
import java.util.Collection;

/**
 *
 * @author ACER
 */
public interface SellerLogisticsService {
    
//    Collection<SellerLogisticsListView> list();
    Collection<SellerLogistics> list();

    SellerLogisticsDetailView add(SellerLogisticsForm form);

    SellerLogisticsDetailView get(Integer logisticsId) throws NotFoundException;

   SellerLogisticsDetailView update(Integer logisticsId, SellerLogisticsForm form) throws NotFoundException;

    void delete(Integer logisticsId) throws NotFoundException;
    
}
