/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

/**
 *
 * @author acer
 */
public class VerifyOtpForm {
//    @NonNull
//    @Size(min=6)

    private int otp;

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

}
