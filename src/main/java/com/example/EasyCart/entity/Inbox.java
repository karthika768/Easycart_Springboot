/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.entity;

import java.util.Date;
import java.util.List;
import javax.activation.DataSource;
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
public class Inbox {
//    Message message;
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer inboxId;
   private String subject;
    private String senderAddress;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
//     @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    private Admin admin;

//    public Admin getAdmin() {
//        return admin;
//    }
//
//    public void setAdmin(Admin admin) {
//        this.admin = admin;
//    }

//    public Inbox( String subject, String senderAddress, String contentType, List<DataSource> attachments) {
//         //To change body of generated methods, choose Tools | Templates.
////           this.user = new User(userId);

//    public Inbox(Integer adminId) {
//        this.admin = new Admin(adminId);
//    }

//
//       
//        this.subject =  message.getSubject();
//        
//        this.senderAddress = message.getFrom();
//         this.image = form.getImage();
//        this.fileName = form.getfileName();
//
//
//        this.status = Status.ACTIVE.value;
//
//        Date dt = new Date();
//        this.createDate = dt;
//        this.updateDate = dt;
//
//    }
//
//    public Inbox() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    public Date getCreateDate() {
        return createDate;
    }
 
    public void setCreateDate(Date createDate) {
        
        this.createDate =createDate;
        
    }
    

    public Integer getInboxId() {
        return inboxId;
    }

    public void setInboxId(Integer inboxId) {
        this.inboxId = inboxId;
    }
    
     private String contentType;
//      private List<DataSource> attachments;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }
//
//    public String getSenderName() {
//        return senderName;
//    }

//    public void setSenderName(String senderName) {
//        this.senderName = senderName;
//    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

//    public List<DataSource> getAttachments() {
//        return attachments;
//    }
//
//    public void setAttachments(List<DataSource> attachments) {
//        this.attachments = attachments;
//    }

    @Override
    public String toString() {
        return "inbox{"+
                "inboxId='"+inboxId+
                "subject='"+subject+
                 "senderAddress='"+senderAddress+'\''+
                          
                          "contentType='"+contentType+'\'';
//                +
//                          "attachments='"+attachments+'}';
                
    }
    
   
}