/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.view;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ACER
 */
public class SellerLogisticsListView {

    private final int logisticsId;
    private final int customerId;
    private final int productId;
    private final int sellerId;
    private final int orderId;
    @Temporal(TemporalType.DATE)
    private final Date shippedDate;
    private final String logisticsStatus;

    public String getLogisticsStatus() {
        return logisticsStatus;
    }

    private final byte status;
    @Temporal(TemporalType.TIMESTAMP)
    private final Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private final Date updateDate;

    public int getLogisticsId() {
        return logisticsId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public byte getStatus() {
        return status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public SellerLogisticsListView(int logisticsId, int customerId, int productId, int sellerId, int orderId, Date shippedDate, String logisticsStatus, byte status, Date createDate, Date updateDate) {
        this.logisticsId = logisticsId;
        this.customerId = customerId;
        this.productId = productId;
        this.sellerId = sellerId;
        this.orderId = orderId;
        this.shippedDate = shippedDate;
        this.logisticsStatus=logisticsStatus;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
