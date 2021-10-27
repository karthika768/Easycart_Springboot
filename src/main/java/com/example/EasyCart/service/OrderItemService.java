/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service;

import com.example.EasyCart.dto.Invoice.InvoiceDto;
import com.example.EasyCart.entity.Invoice;
import com.example.EasyCart.entity.OrderItem;
import com.example.EasyCart.exception.NotFoundException;
import com.example.EasyCart.exception.OrderNotFoundException;

/**
 *
 * @author ACER
 */
public interface OrderItemService {
    
   public void addOrderedProducts(OrderItem orderItem);
   
   OrderItem getOrderItem(Integer orderItemId) throws OrderNotFoundException;
   
//   Invoice createInvoice(InvoiceDto invoiceDto,OrderItem orderItem);
   
    void delete(Integer orderItemId) throws NotFoundException;
}
