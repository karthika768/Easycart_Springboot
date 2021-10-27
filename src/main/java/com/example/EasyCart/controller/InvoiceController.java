/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.controller;

import com.example.EasyCart.Response.ListResponse;
import com.example.EasyCart.Response.ObjectReference;
import com.example.EasyCart.Response.SingleResponse;
import com.example.EasyCart.dto.Invoice.InvoiceDto;
import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Invoice;
import com.example.EasyCart.entity.OrderItem;
import com.example.EasyCart.entity.Seller;
import com.example.EasyCart.service.CustomerService;
import com.example.EasyCart.service.InvoiceService;
import com.example.EasyCart.service.OrderItemService;
import com.example.EasyCart.service.SellerService;
import com.example.EasyCart.service.impl.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import javax.websocket.server.PathParam;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ACER
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins="*")
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private SellerService sellerService;

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/getbyid/{invoiceId}")
    public SingleResponse<InvoiceDto> getInvoice(@PathVariable("invoiceId") Integer invoiceId) {
        return new SingleResponse<>(invoiceService.getInvoice(invoiceId));
    }

    @PostMapping("/{orderItemId}")
    public Invoice createInvoice(@PathVariable("orderItemId") Integer orderItemId, @RequestBody InvoiceDto invoiceDto) {
        Customer customer = customerService.currentCustomer();
        Invoice invoice = invoiceService.createInvoice(orderItemId, invoiceDto);
        return invoice;
    }

    @GetMapping("/all")
    public ListResponse<InvoiceDto> getAll() {
        return new ListResponse<>(invoiceService.getAllInvoices());
    }

    @GetMapping
    public ListResponse<InvoiceDto> getInvoicesByUserId() {
        Customer customer = customerService.currentCustomer();
        return new ListResponse<>(invoiceService.getInvoicesByCustomerId(customer));
    }
    
    @GetMapping("/seller")
    public ListResponse<InvoiceDto> getInvoicesBySellerId() {
       Seller seller = sellerService.currentUser();
        return new ListResponse<>(invoiceService.getInvoicesBySellerId(seller));
    }

//    @GetMapping("/recent")
//    public ListResponse<InvoiceDto> getFirstInvoices5ByUserId(@RequestParam("CustomerId") Integer customerId) {
//        return new ListResponse<>(invoiceService.getFirstInvoices5ByCustomerId( customerId));
//    }
    @GetMapping("/recent")
    public ListResponse<InvoiceDto> getFirstInvoices5ByUserId() {
        Customer customer = customerService.currentCustomer();
        return new ListResponse<>(invoiceService.getFirstInvoices5ByCustomerId(customer));
    }
    
     @GetMapping("/seller/recent")
    public ListResponse<InvoiceDto> getFirstInvoices5BySellerId() {
       Seller seller = sellerService.currentUser();
        return new ListResponse<>(invoiceService.getFirstInvoices5BySellerId(seller));
    }

    @PutMapping("/edit")
    public SingleResponse<InvoiceDto> updateInvoice(@RequestBody InvoiceDto invoice) {
        return new SingleResponse<>(invoiceService.updateInvoice(invoice));
    }

    @DeleteMapping("/delete/{invoiceId}")
    public void delete(@PathVariable("invoiceId") Integer invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
    }
    @Value("${files.path}")
    private String filesPath;

    @GetMapping(value = "/pdf/{invoiceId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamSource> generateInvoicePdf(@PathVariable("invoiceId") Integer invoiceId) throws IOException {
//ResponseEntity<InputStreamSource>    HttpServletResponse response    

//        return ResponseEntity
//                .ok()
//               
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(invoiceService.generateInvoicePdf(invoiceId));
//        response,
//          response.setContentType("application/pdf");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//       
        InputStreamSource file = invoiceService.generateInvoicePdf(invoiceId);
//        Path path = file.getFile()
//                        .toPath();
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE)
                //                Files.probeContentType(path)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=EasyCart_invoice_" + currentDateTime + ".pdf")
                .body(file);
    }

}
