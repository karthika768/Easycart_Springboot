/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.view;

import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.json.Json;
import java.util.Date;

/**
 *
 * @author arun
 */
public class SellerView {
    private final  Integer sellerId;
    private final  String name;
    private final  String cName;
    private final  String address;
    private final  String city;
    private final  String state;
    private final  String country;
    private final  String zipCode;
    private final  String ph;
    private final  String email;
    private final  String password;
    private final  byte status;
    @Json.DateTimeFormat
    private final  Date createDate;
    @Json.DateTimeFormat
    private final  Date updateDate;
    private final  byte[] logo;
    private final  String fileName;

    public SellerView(Seller seller) {
        this.sellerId = seller.getSellerId();
        this.name = seller.getName();
        this.cName = seller.getcName();
        this.address = seller.getAddress();
        this.city = seller.getCity();
        this.state = seller.getState();
        this.country = seller.getCountry();
        this.zipCode = seller.getZipCode();
        this.ph = seller.getPh();
        this.email = seller.getEmail();
        this.password = seller.getPassword();
        this.status = seller.getStatus();
        this.createDate = seller.getCreateDate();
        this.updateDate = seller.getUpdateDate();
        this.logo = seller.getLogo();
        this.fileName = seller.getFileName();
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public String getName() {
        return name;
    }

    public String getcName() {
        return cName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPh() {
        return ph;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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

    public byte[] getLogo() {
        return logo;
    }

    public String getFileName() {
        return fileName;
    }
    
    
}
