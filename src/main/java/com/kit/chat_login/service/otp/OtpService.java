package com.kit.chat_login.service.otp;

import com.kit.chat_login.dto.OtpDto;
import com.kit.chat_login.model.User;
import com.kit.chat_login.model.otp.Otp;
import com.kit.chat_login.model.otp.OtpType;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface OtpService {
    OtpDto generalMailOTP(int user_id, int length, OtpType otpType);
    OtpDto createOtp(String code);
    boolean disableOtp(int id);
    boolean checkOtp(int user_id, String code, OtpType otpType);
    Date getExpOtp();
}
