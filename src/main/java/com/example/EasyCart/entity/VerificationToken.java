/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.entity;


import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author arun
 */
@Entity
@Table(name="verification_token")
public class VerificationToken {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer tokenId;
     
    private String token;
   
   @Column(name="expire_date")
   private Timestamp expireDate;
   
   
   @OneToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
   @JoinColumn(name="seller_id", referencedColumnName="seller_id")
   private Seller seller;

    public VerificationToken() {
    }

    public VerificationToken( Seller seller,String token) {
        this.token = token;
        this.seller = seller;
    }
    

    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Timestamp expireDate) {
        this.expireDate = expireDate;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
   
   
}
