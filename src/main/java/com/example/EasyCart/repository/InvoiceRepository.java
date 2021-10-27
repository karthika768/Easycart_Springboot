/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.repository;

import com.example.EasyCart.entity.Customer;
import com.example.EasyCart.entity.Invoice;
import com.example.EasyCart.entity.Seller;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ACER
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    List<Invoice> findAll();

    List<Invoice> findAllByCustomer(Customer Customer);
//    Optional<Invoice> findByCustomerId((Integer customerId);

    Optional<Invoice> findByInvoiceId(Integer InvoiceId);

//    Optional<Invoice> findByInvoiceIdAndOrderItemId(Integer invoiceId, Integer OrderItemId);
//    List<Invoice> findAllByActive(boolean active);
    List<Invoice> findFirst5ByCustomerOrderByCreateDateDesc(Customer Customer);

    Invoice findByInvoiceIdAndCustomer(Integer invoiceId, Customer Customer);

    List<Invoice> findAllBySeller(Seller seller);

    List<Invoice> findFirst5BySellerOrderByCreateDateDesc(Seller seller);

    Invoice findByInvoiceIdAndSeller(Integer invoiceId, Seller seller);

//    public Object findAllByCustomerIdAndStatus(Integer customerId, Invoice.Status status);
}
