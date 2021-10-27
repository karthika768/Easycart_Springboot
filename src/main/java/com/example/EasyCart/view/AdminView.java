/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.view;

import com.example.EasyCart.entity.Admin;
import com.example.EasyCart.json.Json;
import java.util.Date;

/**
 *
 * @author nirmal
 */
public class AdminView {

    private final int adminId;
    private final String name;
    private final String email;
    
    private final short status;
    @Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;

    public AdminView(Admin admin) {
        this.adminId = admin.getAdminId();
        this.name = admin.getName();
        this.email = admin.getEmail();
        
        this.status = admin.getStatus();
        this.createDate = admin.getCreateDate();
        this.updateDate = admin.getUpdateDate();
    }

    public int getAdminId() {
        return adminId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public short getStatus() {
        return status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
}
