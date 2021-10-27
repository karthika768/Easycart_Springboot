/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "seller_notification")
public class SellerNotification {

    public static enum Status {
        INACTIVE((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }
//    private static final long serialVersionUID = -3819883511505235030L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    private String message;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
//    @JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
    private Seller seller;

    private byte status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
//    @JsonIgnore
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
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

    public SellerNotification(String message, Order order, Customer  customer) {
        this.message = message;
        this.order = order;
        this.customer =  customer;
        this.status = Status.ACTIVE.value;
        Date dt = new Date();
        this.createDate = dt;
    }
     public SellerNotification() {
    }

//    public SellerNotification( String messsage, Order order, Customer customer,Seller seller) {
//      
//        this.messsage = messsage;
//        this.order = order;
//        this.customer = customer;
//        this.seller = seller
   

//        this.status = Status.ACTIVE.value;
//        Date dt = new Date();
//        this.createDate = dt;
//    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notificationId != null ? notificationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SellerNotification)) {
            return false;
        }
        SellerNotification other = (SellerNotification) obj;
        return Objects.equals(this.notificationId, other.notificationId);
    }

    @Override
    public String toString() {
        return "com.example.EasyCart.entity.SellerNotification[ notificationId=" + notificationId
                + " ]";
    }

}
