/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.entity;

import com.example.EasyCart.form.SellerLogisticsForm;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ACER
 */
@Entity
public class SellerLogistics {

    public static enum Status {
        INACTIVE((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logisticsId;
    private Integer customerId;
    private Integer productId;
    private Integer sellerId;
    private Integer orderId;
    @Temporal(TemporalType.DATE)
    private Date shippedDate;
    
    private String logisticsStatus;

    public String getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(String logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }
    private byte status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public SellerLogistics update(SellerLogisticsForm form) {
        this.customerId = form.getCustomerId();
        this.productId = form.getProductId();
        this.sellerId = form.getSellerId();
        this.orderId = form.getOrderId();
        this.shippedDate = form.getShippedDate();
        this.status = Status.ACTIVE.value;

        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;

        return this;
    }

    public SellerLogistics(SellerLogisticsForm form) {

        this.customerId = form.getCustomerId();
        this.productId = form.getProductId();
        this.sellerId = form.getSellerId();
        this.orderId = form.getOrderId();
        this.shippedDate = form.getShippedDate();
        this.status = Status.ACTIVE.value;

        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }

    public SellerLogistics() {
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logisticsId != null ? logisticsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SellerLogistics)) {
            return false;
        }
        SellerLogistics other = (SellerLogistics) obj;
        return Objects.equals(this.logisticsId, other.logisticsId);
    }

    @Override
    public String toString() {
        return "com.example.EasyCart.entity.SellerLogistics[ logisticsId=" + logisticsId + " ]";
    }

}
