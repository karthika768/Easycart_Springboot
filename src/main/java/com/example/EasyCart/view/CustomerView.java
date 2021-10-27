/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.view;

import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Product;
import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.json.Json;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class CustomerView {
    
    private final int customerId;
    private final String name;
    private final String email;
    private final String phone;
//    private final String address;
    
    private final Byte status;
    @Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;

    public CustomerView(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
//        this.address =customer.getAddress();
        this.status = customer.getStatus();
        this.createDate = customer.getCreateDate();
        this.updateDate = customer.getUpdateDate();
    }
    

   

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

//    public String getAddress() {
//        return address;
//    }

    public Byte getStatus() {
        return status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
    
  
   

  
    
  
    
}
