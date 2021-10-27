/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.Response;

/**
 *
 * @author ACER
 */


import lombok.Data;

import java.util.ArrayList;
import java.util.List;



@Data
public class Response {
    private List<String> errors;

    public Response() { this.errors = new ArrayList<>(); }
}