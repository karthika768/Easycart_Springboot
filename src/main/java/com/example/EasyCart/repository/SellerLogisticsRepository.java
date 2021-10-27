/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.repository;

import com.example.EasyCart.entity.SellerLogistics;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.repository.Repository;

/**
 *
 * @author ACER
 */
public interface SellerLogisticsRepository extends Repository<SellerLogistics, Integer> {

    Optional<SellerLogistics> findById(Integer logisticsId);

//     Collection<SellerLogisticsListView> findAllByProductProductId(Integer productId);
//      Collection<SellerLogisticsListView> findAllByOrderOrderId(Integer orderId);
//       Collection<SellerLogisticsListView> findBylogisticsIdAndSellerSellerId(Integer logisticsId,Integer sellerId);
//     Collection<SellerLogisticsListView> findAllByCustomerCustomerId(Integer customerId);
    SellerLogistics save(SellerLogistics sellerLogistics);

    Collection<SellerLogistics> findAll();

    public void delete(SellerLogistics sellerLogistics);

}
