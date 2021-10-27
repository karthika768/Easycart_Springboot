/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.view;

import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Product;
import com.example.EasyCart.json.Json;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author ACER
 */
public class CartListView {
    
   
    private final Integer id;

    @Json.DateTimeFormat
    private Date createDate;

   

   
    private final Product product;

    private  final Customer customer;
    private final int quantity;
     public CartListView(Integer id, Date createDate, Product product, Customer customer, int quantity) {
        this.id = id;
        this.createDate = createDate;
        this.product = product;
        this.customer = customer;
       
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Product getProduct() {
        return product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getQuantity() {
        return quantity;
    }

   

    
}
