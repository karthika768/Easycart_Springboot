/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.dto.Invoice;

import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Invoice;
import com.example.EasyCart.entity.Order;
import com.example.EasyCart.entity.OrderItem;
import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.view.CustomerView;
import com.example.EasyCart.view.SellerView;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.Random;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author ACER
 */
@Data
public class InvoiceDto {

    private Integer invoiceId;

    Random random = new Random();

    int invoiceNo = random.ints(100000000, 999999999).findFirst().getAsInt();

    private String invoiceNumber = "IN" + Integer.toString(invoiceNo);

    @NotBlank
    private Customer customer;

    @NotBlank
    private Order order;
    
    @NotBlank
    private Seller seller;

    @NotBlank
    private OrderItem orderItem;

//    private Set<InvoiceVatDTO> invoiceVats;
    public InvoiceDto(Invoice invoice) {
        this.setOrderItem(invoice.getOrderitems());
        this.setOrder(invoice.getOrderitems().getOrder());
        this.setCustomer(invoice.getOrderitems().getOrder().getCustomer());
        this.setSeller(invoice.getOrderitems().getProduct().getSellerId());
    }

    public InvoiceDto() {
    }

}
