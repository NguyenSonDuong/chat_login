package com.kit.chat_login.mapping;

import com.kit.chat_login.dto.OtpDto;
import com.kit.chat_login.model.otp.Otp;

public class OtpMapping {
    public static OtpDto convert(Otp otp){
        OtpDto otpDto = new OtpDto(
                otp.getStatus(),
                otp.getCreate_at(),
                otp.getCode(),
                otp.getCode(),
                otp.getCongfirm(),
                otp.getOtp_type(),
                otp.getCode_exp()
        );
        return otpDto;
    }
}
