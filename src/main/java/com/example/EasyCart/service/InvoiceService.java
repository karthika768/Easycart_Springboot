/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service;

import com.example.EasyCart.dto.Invoice.InvoiceDto;
import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Invoice;
import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.exception.AuthenticationFailException;
import com.example.EasyCart.exception.NotFoundException;
import java.util.List;
import org.springframework.core.io.InputStreamSource;

/**
 *
 * @author ACER
 */
public interface InvoiceService {

    public List<InvoiceDto> getInvoicesByCustomerId(Customer customer);

    public List<InvoiceDto> getInvoicesBySellerId(Seller seller);

    public List<InvoiceDto> getFirstInvoices5ByCustomerId(Customer customer);

    public List<InvoiceDto> getFirstInvoices5BySellerId(Seller seller);

    public List<InvoiceDto> getAllInvoices();

    public InvoiceDto getInvoice(Integer invoiceId);

    public Invoice createInvoice(Integer orderItemId, InvoiceDto invoiceDto) throws AuthenticationFailException;

    public InvoiceDto updateInvoice(InvoiceDto invoice);

    public void deleteInvoice(Integer invoiceId) throws NotFoundException;

    public InputStreamSource generateInvoicePdf(Integer invoiceId);

}
