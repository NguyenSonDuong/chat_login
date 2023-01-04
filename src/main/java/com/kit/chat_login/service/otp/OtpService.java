package com.kit.chat_login.service.otp;

import com.kit.chat_login.dto.OtpDto;
import com.kit.chat_login.model.User;
import com.kit.chat_login.model.otp.Otp;
import com.kit.chat_login.model.otp.OtpConfig;
import com.kit.chat_login.model.otp.OtpType;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public interface OtpService {

    String randomOtp(int length);
    Otp checkExitsOtp(User user);
    OtpDto createOtp(User user, int length);

    Otp checkMailOtp(User user, String code, OtpConfig config);
    Otp checkAppOtp(User user, String code);

    OtpDto checkOtp(User name, String code, OtpType otpType, OtpConfig otpConfig);

    OtpDto checkOtp(User user, String code, OtpType otpType);

    boolean sendCodeOtp(String uuid );

    boolean checkCodeOtp(String uuid, String code, OtpType otpType);

    Date getExpOtp();
    String generalQR2fa(User user) throws UnsupportedEncodingException;
}
