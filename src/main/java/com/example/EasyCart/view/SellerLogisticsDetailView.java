/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.view;

import com.example.EasyCart.entity.SellerLogistics;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class SellerLogisticsDetailView extends SellerLogisticsListView {

    public SellerLogisticsDetailView(SellerLogistics sellerLogistics) {
        super(
                sellerLogistics.getLogisticsId(),
                sellerLogistics.getCustomerId(),
                sellerLogistics.getProductId(),
                sellerLogistics.getSellerId(),
                sellerLogistics.getOrderId(),
                sellerLogistics.getShippedDate(),
                sellerLogistics.getLogisticsStatus(),
                sellerLogistics.getStatus(),
                sellerLogistics.getCreateDate(),
                sellerLogistics.getUpdateDate()
        );
    }

}
