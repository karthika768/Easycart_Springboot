/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.entity;

import com.example.EasyCart.form.SellerForm;
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
 * @author karthika
 */
@Entity
public class Seller {

    public Seller(Seller seller) {
      this.sellerId = seller.getSellerId();
    }
    public static enum Status {
        DELETED((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    
}
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sellerId;
    private String name;
    private String cName;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String ph;
    private String email;
    private String password;
    private byte status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    private byte[] logo;
    private String fileName;
    private boolean enabled;

    public Seller(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Seller() {
    }

    public Seller(String name,String cName,String address,String city,String state,String country,String zipCode,String ph,String email,String password,byte[] logo,String fileName) {
        this.name = name;
        this.cName = cName;
        this.address = address;
        this.city = city;
        this.state = state ;
        this.country = country ;
        this.zipCode = zipCode ;
        this.ph = ph;
        this.email = email;
        this.password =password;
        this.status = Status.ACTIVE.value;

        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
        this.logo = logo;
        this.fileName =fileName;
        this.enabled=false;
    }
   
     
     public Seller update(SellerForm form) {
        this.name = form.getName();
        this.cName = form.getcName();
        this.address = form.getAddress();
        this.city = form.getCity();
        this.state = form.getState();
        this.country = form.getCountry();
        this.zipCode = form.getZipCode();
        this.ph = form.getPh();
        this.email = form.getEmail();
        this.password =form.getPassword();
        this.status = Status.ACTIVE.value;
        Date dt = new Date();
        this.updateDate = dt;
        this.logo = form.getLogo();
        this.fileName = form.getFileName();
         this.enabled=true;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sellerId != null ? sellerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Seller)) {
            return false;
        }
        return Objects.equals(sellerId, ((Seller) object).sellerId);
    }

    @Override
    public String toString() {
       return "com.example.EasyCart.entity.Seller[ sellerId=" + sellerId + " ]";
    }
//    public boolean isAccountVerified(){
//    return accountVerified;
//    } 
//    
    
    
    
}
