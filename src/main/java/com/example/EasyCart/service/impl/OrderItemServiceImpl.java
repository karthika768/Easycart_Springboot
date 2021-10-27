/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service.impl;

import com.example.EasyCart.Response.ObjectReference;
import com.example.EasyCart.dto.Invoice.InvoiceDto;
import com.example.EasyCart.entity.Invoice;
import com.example.EasyCart.entity.OrderItem;
import com.example.EasyCart.exception.NotFoundException;
import com.example.EasyCart.exception.OrderNotFoundException;
import com.example.EasyCart.repository.OrderItemRepository;
import com.example.EasyCart.security.util.CustomerSecurityUtil;
import com.example.EasyCart.service.OrderItemService;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */

@Service
public class OrderItemServiceImpl implements OrderItemService{
    
    
     @Autowired
    private OrderItemRepository orderItemRepository;
     
     
      @Autowired
    private InvoiceServiceImpl invoiceServiceImpl;
     
       private final ModelMapper modelMapper = new ModelMapper();
     
    @Override 
    @Transactional
    public void addOrderedProducts(OrderItem orderItem) {
        InvoiceDto invoiceDto = new InvoiceDto();
        
        
        orderItemRepository.save(orderItem);
//        InvoiceDto invoiceDto =new InvoiceDto();
        invoiceServiceImpl.createInvoice(orderItem.getOrderItemId(), invoiceDto);
        
    }
    
    
    @Override 
    @Transactional
    public OrderItem  getOrderItem(Integer orderItemId) throws OrderNotFoundException {
        Optional<OrderItem> order = orderItemRepository.findById(orderItemId);
        if (order.isPresent()) {
            return order.get();
        }
        throw new OrderNotFoundException("Order not found");
    }
    
//    @Override
//    @Transactional
//    public Invoice createInvoice(InvoiceDto invoiceDto,OrderItem orderItem) {
//        Invoice invoice = modelMapper.map(invoiceDto, Invoice.class);
//        
//        
//        orderItem =  getOrderItem(orderItem.getOrderItemId());
//        invoiceDto.setOrderItem(orderItem);
//         return invoice;
//   
//    }
   
    
     @Override
    @Transactional
    public void delete(Integer orderItemId) throws NotFoundException {
        orderItemRepository.delete(
                orderItemRepository.findByOrderItemId(orderItemId)
                        .orElseThrow(NotFoundException::new)
        );
    }
    
}
