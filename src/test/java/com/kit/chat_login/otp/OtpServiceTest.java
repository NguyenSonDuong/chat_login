package com.kit.chat_login.otp;

import com.kit.chat_login.dto.OtpDto;
import com.kit.chat_login.model.otp.OtpType;
import com.kit.chat_login.service.otp.OtpService;
import com.kit.chat_login.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class OtpServiceTest {

    @Autowired
    OtpService otpService;

    @Test
    public void generalOtpTest(){

//        OtpDto otp = otpService.generalMailOTP(2, 5);
//        assertThat(otp).isNotNull();
    }

    @Test
    public void checkOtpApp(){
        String otp = "374509";
//        OtpDto otpDto = otpService.checkOtp(3,otp,OtpType.APP_OTP);
//        assertThat(otpDto).isNotNull();
    }

    @Test
    public void generalQr2fa(){
//        try {
//            String url = otpService.generalQR2fa(3);
//            System.out.println(url);
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
    }
    @Test
    public void sendOTPHtml(){
        boolean url = otpService.sendCodeOtp("d63f8020-7c2e-4c6e-a742-b45e72b7b112");
        System.out.println(url);
    }
}
