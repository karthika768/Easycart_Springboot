/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author ACER
 */
@Data
@AllArgsConstructor
public class ObjectReference {
    private Integer invoiceId;
    private Integer OrderItemId;
}