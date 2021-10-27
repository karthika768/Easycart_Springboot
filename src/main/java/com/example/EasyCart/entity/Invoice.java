/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.entity;

import com.example.EasyCart.dto.Invoice.InvoiceDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "invoices")
public class Invoice {
    
    
    
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
//     @Column(name = "")
    private Integer  invoiceId;
    
    
    private String invoiceNumber ;
    
//    invoiceNumber = Integer.toString(invoiceNo);
    
    
     @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @JsonIgnore
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
     @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
     @JsonIgnore
     private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    @OneToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
    @JsonIgnore
    private Seller seller;

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
   

     @OneToOne
    @JoinColumn(name = "order_item_id", referencedColumnName = "order_item_id")
    private OrderItem orderitems;
     
      private byte status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;
   



    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public OrderItem getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(OrderItem orderitems) {
        this.orderitems = orderitems;
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

    public Invoice() {
    }



    public Invoice(String invoiceNumber, Customer customer,Order order, Seller seller ,OrderItem orderitems, byte status, Date createDate) {
        this.invoiceNumber = invoiceNumber;
        this.customer = customer;
        this.order = order;
        this.seller = seller;
        this.orderitems = orderitems;
        this.status = status;
        this.createDate = createDate;
    }
}

