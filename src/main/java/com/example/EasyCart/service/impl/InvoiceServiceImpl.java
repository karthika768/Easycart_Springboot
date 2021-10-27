/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service.impl;

import com.example.EasyCart.Response.ObjectReference;
import com.example.EasyCart.dto.Invoice.InvoiceDto;
import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Invoice;
import static com.example.EasyCart.entity.Invoice.Status.ACTIVE;
import com.example.EasyCart.entity.Order;
import com.example.EasyCart.entity.OrderItem;
import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.exception.AuthenticationFailException;
import com.example.EasyCart.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import com.example.EasyCart.repository.InvoiceRepository;
import com.example.EasyCart.repository.OrderItemRepository;
import com.example.EasyCart.security.util.CustomerSecurityUtil;
import com.example.EasyCart.service.CustomerService;
import com.example.EasyCart.service.InvoiceService;
import com.example.EasyCart.service.OrderItemService;
import com.example.EasyCart.service.SellerService;
import com.example.EasyCart.utils.PdfGenerator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.UrlResource;

/**
 *
 * @author ACER
 */
@Service
//@RequiredArgsConstructor
public class InvoiceServiceImpl implements  InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private SellerService sellerService;
        
//     private final InvoiceRepository invoiceRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    
    @Override
    public List<InvoiceDto> getInvoicesByCustomerId(Customer customer) {
        return invoiceRepository.findAllByCustomer(customerService.currentCustomer()).stream()
                .map(invoice -> modelMapper.map(invoice, InvoiceDto.class)).collect(Collectors.toList());
    }
    
    
    @Override
    public List<InvoiceDto> getInvoicesBySellerId(Seller seller) {
        return invoiceRepository.findAllBySeller(sellerService.currentUser()).stream()
                .map(invoice -> modelMapper.map(invoice, InvoiceDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDto> getFirstInvoices5ByCustomerId(Customer customer) {
        return invoiceRepository.findFirst5ByCustomerOrderByCreateDateDesc(customerService.currentCustomer()).stream()
                .map(invoice -> modelMapper.map(invoice, InvoiceDto.class)).collect(Collectors.toList());
    }
    
    @Override
    public List<InvoiceDto> getFirstInvoices5BySellerId(Seller seller) {
        return invoiceRepository.findFirst5BySellerOrderByCreateDateDesc(sellerService.currentUser()).stream()
                .map(invoice -> modelMapper.map(invoice, InvoiceDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDto> getAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(invoice -> modelMapper.map(invoice, InvoiceDto.class)).collect(Collectors.toList());
    }

    @Override
    public InvoiceDto getInvoice(Integer invoiceId) {
        Invoice invoice = invoiceRepository.findByInvoiceId(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not exist id = " + invoiceId));

        return modelMapper.map(invoice, InvoiceDto.class);
//        return invoice;
    }

    @Override
    public Invoice createInvoice(Integer orderItemId, InvoiceDto invoiceDto) throws AuthenticationFailException {
        Invoice invoice = modelMapper.map(invoiceDto, Invoice.class);
//     if(invoice.getCustomer() == customerService.currentCustomer()){

        invoice.setStatus((byte) 1);
        Date dt = new Date();
        invoice.setCreateDate(dt);
//        Integer orderId=
        OrderItem orderItem = orderItemService.getOrderItem(orderItemId);
        System.out.println("orderItem : " + orderItem.getOrderItemId());

        invoiceDto.setOrderItem(orderItem);
        invoice.setOrderitems(orderItem);
        invoice.setCustomer(orderItem.getOrder().getCustomer());
        invoice.setOrder(orderItem.getOrder());
        invoice.setSeller(orderItem.getProduct().getSellerId());

        invoiceRepository.save(invoice);
        System.out.println("invoice : " + invoice);
        orderItem.setInvoiceId(invoice.getInvoiceId());
        return invoice;
////        return new ObjectReference(invoice.getInvoiceId());

//return orderItemRepository.findByOrderItemId(orderItemId)
//        .map((invoice) -> {
//                    return new Invoice(invoiceRepository.save(invoice));
//                }).orElseThrow(NotFoundException::new);
//     }
//     else{
//         System.out.println(" You are not current customer");
//     }
//     return invoice;
    }

    @Override
    public InvoiceDto updateInvoice(InvoiceDto invoice) {
        Invoice invoiceDs = invoiceRepository.findByInvoiceId(invoice.getInvoiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found id: " + invoice.getInvoiceId()));
        modelMapper.map(invoice, invoiceDs);
        invoiceDs.setStatus((byte) 1);

        invoiceRepository.save(invoiceDs);
        return invoice;
    }

    /**
     *
     * @param invoiceId
     * @throws NotFoundException
     */
    
    @Override
    @Transactional
    public void deleteInvoice(Integer invoiceId) throws NotFoundException {

        invoiceRepository.delete(
                invoiceRepository.findByInvoiceId(invoiceId)
                        .orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public InputStreamSource generateInvoicePdf(Integer invoiceId) {
//        InputStreamSource HttpServletResponse response,
System.out.println("InvoiceId "+invoiceId);
        Invoice invoice = invoiceRepository.findByInvoiceId(invoiceId)
                
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not exist id = " + invoiceId));

        PdfGenerator pdfGenerator = new PdfGenerator();
        ByteArrayInputStream bis = pdfGenerator.generate(invoice);
        pdfGenerator.generate( invoice);
//        response,

        return new InputStreamResource(bis);
    }



}
