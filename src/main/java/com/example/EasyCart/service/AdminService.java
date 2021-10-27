/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service;

import com.example.EasyCart.entity.Admin;
import com.example.EasyCart.entity.Inbox;
import com.example.EasyCart.exception.BadRequestException;
import com.example.EasyCart.exception.NotFoundException;
import com.example.EasyCart.form.AdminForm;
import com.example.EasyCart.form.LoginForm;
import com.example.EasyCart.view.AdminView;
import com.example.EasyCart.view.LoginView;
import java.util.Collection;
import java.util.List;
import javax.mail.Message;
import org.springframework.validation.Errors;

/**
 *
 * @author nirmal
 */
public interface AdminService {

    AdminView add(AdminForm form);

    Collection<Admin> list();

    AdminView currentUser();

    LoginView login(LoginForm form, Errors errors) throws BadRequestException;

    LoginView refresh(String refreshToken) throws BadRequestException;

//    AdminView update(Integer adminId, AdminForm form) throws NotFoundException;
    AdminView get(Integer adminId) throws NotFoundException;

//    void delete(Integer adminId) throws NotFoundException;
    Inbox receiveEmail(String pop3Host, String storeType, String admin, String password);
}
