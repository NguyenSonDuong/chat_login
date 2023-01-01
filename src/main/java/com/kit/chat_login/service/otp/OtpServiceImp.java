package com.kit.chat_login.service.otp;

import com.kit.chat_login.dto.OtpDto;
import com.kit.chat_login.exception.OtpException;
import com.kit.chat_login.mapping.OtpMapping;
import com.kit.chat_login.message.otp.OtpErrorMessage;
import com.kit.chat_login.model.User;
import com.kit.chat_login.model.otp.Otp;
import com.kit.chat_login.model.otp.OtpConfig;
import com.kit.chat_login.model.otp.OtpType;
import com.kit.chat_login.repository.otp.OtpRepository;
import com.kit.chat_login.service.user.UserService;
import org.jboss.aerogear.security.otp.Totp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class OtpServiceImp implements OtpService{

    @Autowired
    UserService userService;
    @Autowired
    private OtpRepository otpRepository;


    @Override
    public OtpDto generalMailOTP(int user_id, int length, OtpType otpType) {
        User user = userService.searchUserById(user_id);
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(9));
        }
        Otp otp = new Otp();
        otp.setOtp_type(otpType);
        otp.setUser(user);
        otp.setCode(code.toString());
        otp.setCode_exp(getExpOtp());
        otp.setConfig(OtpConfig.NOT_USE);
        Otp otpSave = otpRepository.save(otp);
        if(otpSave == null)
            throw new OtpException(OtpErrorMessage.CREATE_ERROR);
        return OtpMapping.convert(otp);
    }

    @Override
    public OtpDto createOtp(String code) {
        return null;
    }

    @Override
    public boolean disableOtp(int id) {
        return false;
    }

    @Override
    public boolean checkOtp(int user_id,String code, OtpType otpType) {
        if (otpType == OtpType.MAIL_OTP){
            User user = userService.searchUserById(user_id);
            Otp otp = otpRepository.findByUserAndCode(user,code);
            if(otp == null)
                throw new OtpException(OtpErrorMessage.NOT_FOUND);
            if(otp.getCode_exp().before(new Date())){
                throw new OtpException(OtpErrorMessage.OTP_EXP);
            }
            if(otp.getConfig() != OtpConfig.NOT_USE){
                throw new OtpException(OtpErrorMessage.OTP_EXP);
            }
            return true;
        }
        return false;
    }

    @Override
    public Date getExpOtp() {
        return new Date(new Date().getTime() + 180000);
    }
}
