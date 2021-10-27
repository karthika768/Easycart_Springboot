/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.utils;

import com.example.EasyCart.entity.Invoice;
import com.example.EasyCart.entity.OrderItem;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ACER
 */
public class PdfGenerator {

    public  ByteArrayInputStream generate( Invoice invoice) {
//        ByteArrayInputStream  HttpServletResponse response,

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
            BaseFont helvetica_bold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1250, BaseFont.EMBEDDED);
            Font helvetica9 = new Font(helvetica, 9);
            Font helvetica_bold18 = new Font(helvetica_bold, 18);
            Font helvetica_bold14 = new Font(helvetica_bold, 14);
            Font helvetica_bold12 = new Font(helvetica_bold, 12);
            Font helvetica14 = new Font(helvetica, 14);
            Font helvetica12 = new Font(helvetica, 12);

            PdfPTable mainTable = new PdfPTable(1);
            mainTable.setWidthPercentage(100);
            mainTable.setWidths(new int[]{1});

            PdfPTable headTable = new PdfPTable(3);
            headTable.setWidths(new int[]{1, 1, 1});

            PdfPCell companySealCell = new PdfPCell(new Phrase("Stamp", helvetica9));
            companySealCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            companySealCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            companySealCell.setMinimumHeight(120);
            headTable.addCell(companySealCell);

            PdfPCell invoiceTitleCell = new PdfPCell(new Phrase("INVOICE", helvetica_bold18));
            invoiceTitleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            invoiceTitleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            invoiceTitleCell.setMinimumHeight(120);
            headTable.addCell(invoiceTitleCell);

            PdfPTable datesTable = new PdfPTable(1);

            PdfPCell invoiceNumberCell = new PdfPCell(new Phrase("Invoice Number: " + Chunk.NEWLINE + invoice.getInvoiceNumber(), helvetica14));
            invoiceNumberCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            invoiceNumberCell.setMinimumHeight(40);
            datesTable.addCell(invoiceNumberCell);

            PdfPCell createDateCell = new PdfPCell(new Phrase("Date Of Issue: " + Chunk.NEWLINE + invoice.getCreateDate(), helvetica14));
            createDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            createDateCell.setMinimumHeight(40);
            datesTable.addCell(createDateCell);

            PdfPCell saleDateCell = new PdfPCell(new Phrase("Date Of Sale: " + Chunk.NEWLINE + invoice.getOrder().getCreateDate(), helvetica14));
            saleDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            saleDateCell.setMinimumHeight(40);
            datesTable.addCell(saleDateCell);

            headTable.addCell(datesTable);

            PdfPTable informationTable = new PdfPTable(3);

            PdfPCell userCell = new PdfPCell(new Phrase(
                    //                    "Customer Name: " + invoice.getCustomer().getName() + Chunk.NEWLINE +
                    //                    "Address: " + invoice.getOrder().getStreet() + ", " + invoice.getUser().getPostcode() + " " + invoice.getUser().getCity() + Chunk.NEWLINE +
                    //                    "NIP: " + invoice.getUser().getNip() + Chunk.NEWLINE +
                    "Customer Name : " + invoice.getOrder().getCustomer().getName() + Chunk.NEWLINE
                    + "Address: " + invoice.getOrderitems().getOrder().getShippingAddress() + Chunk.NEWLINE
                    + "Phone: " + invoice.getOrder().getCustomer().getPhone() + Chunk.NEWLINE,
                    helvetica12));
            informationTable.addCell(userCell);

            PdfPCell clientCell = new PdfPCell(new Phrase(
                    "Seller Name : " + invoice.getOrderitems().getProduct().getSellerId().getName() + Chunk.NEWLINE
                    + "Addreds : " + invoice.getOrderitems().getProduct().getSellerId().getcName() + ", " + invoice.getOrderitems().getProduct().getSellerId().getAddress() + ", " + invoice.getOrderitems().getProduct().getSellerId().getCity() + ", " + invoice.getOrderitems().getProduct().getSellerId().getState() + ", " + invoice.getOrderitems().getProduct().getSellerId().getCountry() + Chunk.NEWLINE
                    + "PIN : " + invoice.getOrderitems().getProduct().getSellerId().getZipCode() + Chunk.NEWLINE
                    + "Contact Number : " + invoice.getOrderitems().getProduct().getSellerId().getPh() + Chunk.NEWLINE,
                    helvetica12));
            informationTable.addCell(clientCell);

            PdfPCell paymentTypeCell = new PdfPCell(new Phrase("Payment Method : " + invoice.getOrder().getPaymentType(), helvetica12));
            informationTable.addCell(paymentTypeCell);

//            PdfPCell paymentDateCell = new PdfPCell(new Phrase("Termin płatności: " + invoice.getPaymentDate().format(df), helvetica12));
//            informationTable.addCell(paymentDateCell);
            PdfPTable positionsTable = new PdfPTable(7);
            positionsTable.setWidths(new int[]{3, 2, 2, 2, 2, 2, 2,});

            PdfPCell headerPositionCell;

            headerPositionCell = new PdfPCell(new Phrase("Invoice No.", helvetica_bold12));
            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(headerPositionCell);

            headerPositionCell = new PdfPCell(new Phrase("Name", helvetica_bold12));
            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(headerPositionCell);

            headerPositionCell = new PdfPCell(new Phrase("Quantity", helvetica_bold12));
            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(headerPositionCell);

            headerPositionCell = new PdfPCell(new Phrase("Unit", helvetica_bold12));
            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(headerPositionCell);

//            headerPositionCell = new PdfPCell(new Phrase("Net Price", helvetica_bold12));
//            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            positionsTable.addCell(headerPositionCell);
            headerPositionCell = new PdfPCell(new Phrase("Unit Price", helvetica_bold12));
            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(headerPositionCell);

            headerPositionCell = new PdfPCell(new Phrase("MSRP", helvetica_bold12));
            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(headerPositionCell);
//
//            headerPositionCell = new PdfPCell(new Phrase("Kwota VAT", helvetica_bold12));
//            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            positionsTable.addCell(headerPositionCell);
            headerPositionCell = new PdfPCell(new Phrase("Gross Amount", helvetica_bold12));
            headerPositionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(headerPositionCell);

            int idx = 1;
//            for(InvoicePosition position : invoice.getInvoicePositions()) {
//            for (OrderItem item : invoice.getOrderitems()) {

//              OrderItem item =  invoice.getOrderitems();
//            PdfPTable positionsTable = new PdfPTable(9);
            PdfPCell positionCell;

            positionCell = new PdfPCell(new Phrase(invoice.getInvoiceNumber(), helvetica12));
            positionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(positionCell);

            positionCell = new PdfPCell(new Phrase(invoice.getOrderitems().getProduct().getProductName(), helvetica12));
            positionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            positionsTable.addCell(positionCell);

            String str1 = Integer.toString(invoice.getOrderitems().getQuantity());
            System.out.println("str1 : " + str1);
            positionCell = new PdfPCell(new Phrase(str1, helvetica12));
            positionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            positionsTable.addCell(positionCell);

            positionCell = new PdfPCell(new Phrase(str1, helvetica12));
            positionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            positionsTable.addCell(positionCell);

//                String str2 = Integer.toString(item.getProduct().getUnitPrice());
            positionCell = new PdfPCell(new Phrase(invoice.getOrderitems().getProduct().getUnitPrice().toString(), helvetica12));
            positionCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            positionsTable.addCell(positionCell);

            positionCell = new PdfPCell(new Phrase(invoice.getOrderitems().getProduct().getMsrp().toString(), helvetica12));
            positionCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            positionsTable.addCell(positionCell);
//
//                positionCell = new PdfPCell(new Phrase(position.getVatType().getName(), helvetica12));
//                positionCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                positionsTable.addCell(positionCell);
//
//                positionCell = new PdfPCell(new Phrase(position.getVatValue().toString(), helvetica12));
//                positionCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                positionsTable.addCell(positionCell);

            Double price = invoice.getOrderitems().getPrice();
            Double quantity = new Double(invoice.getOrderitems().getQuantity());
            Double Gross = price * quantity;

            positionCell = new PdfPCell(new Phrase(Gross.toString(), helvetica12));
            positionCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            positionsTable.addCell(positionCell);

//                idx++;
            PdfPTable summaryMainTable = new PdfPTable(2);
            summaryMainTable.setWidths(new int[]{7, 10});

            PdfPTable summaryTable = new PdfPTable(2);
            summaryTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
            summaryTable.setWidths(new int[]{1, 1});

            PdfPCell summaryAllCell = new PdfPCell(new Phrase("Total :", helvetica_bold12));
            summaryAllCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            summaryTable.addCell(summaryAllCell);

            PdfPCell summaryNetValueCell = new PdfPCell(new Phrase(Gross.toString(), helvetica_bold12));
            summaryNetValueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            summaryTable.addCell(summaryNetValueCell);

//            PdfPCell summaryVatTypeCell = new PdfPCell(new Phrase("X", helvetica_bold12));
//            summaryVatTypeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            summaryTable.addCell(summaryVatTypeCell);
//
//            PdfPCell summaryVatValueCell = new PdfPCell(new Phrase(invoice.getVatAmount().toString(), helvetica_bold12));
//            summaryVatValueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            summaryTable.addCell(summaryVatValueCell);
//            PdfPCell summaryGrossValueCell = new PdfPCell(new Phrase(invoice.getOrder().getTotalPrice().toString(), helvetica_bold12));
//            summaryGrossValueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            summaryTable.addCell(summaryGrossValueCell);
//            PdfPCell summaryIncludeCell = new PdfPCell(new Phrase("W tym:", helvetica_bold12));
//            summaryIncludeCell.setRowspan(invoice.getInvoiceVats().size());
//            summaryIncludeCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            summaryIncludeCell.setVerticalAlignment(Element.ALIGN_TOP);
//            summaryTable.addCell(summaryIncludeCell);
//
//            for (InvoiceVat vat : invoice.getInvoiceVats()) {
//                PdfPCell vatCell;
//
//                vatCell = new PdfPCell(new Phrase(vat.getNetValue().toString(), helvetica12));
//                vatCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                summaryTable.addCell(vatCell);
//
//                vatCell = new PdfPCell(new Phrase(vat.getVatType().getName(), helvetica12));
//                vatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                summaryTable.addCell(vatCell);
//
//                vatCell = new PdfPCell(new Phrase(vat.getVatValue().toString(), helvetica12));
//                vatCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                summaryTable.addCell(vatCell);
//
//                vatCell = new PdfPCell(new Phrase(vat.getGrossValue().toString(), helvetica12));
//                vatCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                summaryTable.addCell(vatCell);
//            }
            PdfPCell emptyCell = new PdfPCell(new Phrase(""));
            summaryMainTable.addCell(emptyCell);
            summaryMainTable.addCell(summaryTable);

            PdfPCell toPayCell = new PdfPCell(new Phrase("Gross Amount : " + Gross + " Rupees", helvetica_bold14));

            PdfPTable signatureTable = new PdfPTable(2);

            PdfPCell clientSignatureCell = new PdfPCell(new Phrase("Name and Surname isn't authhorized to collect the document", helvetica9));
            clientSignatureCell.setPaddingLeft(10);
            clientSignatureCell.setPaddingRight(10);
            clientSignatureCell.setMinimumHeight(100);
            clientSignatureCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            clientSignatureCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            signatureTable.addCell(clientSignatureCell);

            PdfPCell userSignatureCell = new PdfPCell(new Phrase("Name and surname of the person authorized to issue the document", helvetica9));
            clientSignatureCell.setPaddingLeft(10);
            clientSignatureCell.setPaddingRight(10);
            userSignatureCell.setMinimumHeight(100);
            userSignatureCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            userSignatureCell.setVerticalAlignment(Element.ALIGN_BOTTOM);

            signatureTable.addCell(userSignatureCell);

            mainTable.addCell(headTable);
            mainTable.addCell(informationTable);
            mainTable.addCell(positionsTable);

            mainTable.addCell(summaryMainTable);
            mainTable.addCell(toPayCell);
            mainTable.addCell(signatureTable);
//            
//              public void export(HttpServletResponse response) throws DocumentException, IOException {
//        Document document = new Document(PageSize.A4);
//        PdfWriter.getInstance(document, response.getOutputStream());
//         
//        document.open();
//        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//        font.setSize(18);
//        font.setColor(Color.BLUE);

            PdfWriter.getInstance(document, out);
//            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.add(mainTable);

            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(PdfGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {

        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
