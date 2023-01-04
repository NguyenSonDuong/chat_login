package com.kit.chat_login.service.otp;

import com.kit.chat_login.dto.OtpDto;
import com.kit.chat_login.exception.OtpException;
import com.kit.chat_login.help.Help;
import com.kit.chat_login.mapping.OtpMapping;
import com.kit.chat_login.message.otp.OtpErrorMessage;
import com.kit.chat_login.model.StatusModel;
import com.kit.chat_login.model.User;
import com.kit.chat_login.model.otp.Otp;
import com.kit.chat_login.model.otp.OtpConfig;
import com.kit.chat_login.model.otp.OtpType;
import com.kit.chat_login.repository.otp.OtpRepository;
import com.kit.chat_login.service.email.EmailService;
import com.kit.chat_login.service.user.UserService;
import org.jboss.aerogear.security.otp.Totp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Random;

@Component
public class OtpServiceImp implements OtpService{


    @Value("${security.otp.2fa.qr_prefix}")
    private String QR_PREFIX;
    @Value("${spring.application.name}")
    private String APP_NAME;

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;
    @Autowired
    private OtpRepository otpRepository;


    @Override
    public String randomOtp(int length) {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(9));
        }
        return code.toString();
    }

    @Override
    public Otp checkExitsOtp(User user) {
        Page<Otp> otp2 = otpRepository.findOtpNewCreate(user ,Pageable.ofSize(1));
        if(otp2 == null || !otp2.isFirst() || otp2.isEmpty())
            return null;
        Otp otp = otp2.stream().findFirst().get();
        if(otp.getCode_exp().after(new Date())){
            return otp;
        }
        return null;
    }

    @Override
    public OtpDto createOtp(User user, int length) {
        Otp otp = checkExitsOtp(user);
        if(otp != null){
            return OtpMapping.convert(otp);
        }
        String code = randomOtp(length);
        otp = new Otp();
        otp.setOtp_type(OtpType.MAIL_OTP);
        otp.setUser(user);
        otp.setStatus(StatusModel.ACTIVE);
        otp.setCode(code.toString());
        otp.setCode_exp(getExpOtp());
        otp.setConfig(OtpConfig.NOT_USE);
        Otp otpSave = otpRepository.save(otp);
        if(otpSave == null)
            throw new OtpException(OtpErrorMessage.CREATE_ERROR);
        return OtpMapping.convert(otp);
    }

    @Override
    public Otp checkMailOtp(User user, String code, OtpConfig config) {
        Otp otp = otpRepository.findByUserAndCodeAndConfigAndStatus(user,code, OtpConfig.NOT_USE, StatusModel.ACTIVE);
        if(otp == null)
            throw new OtpException(OtpErrorMessage.NOT_FOUND);
        if(otp.getCode_exp().before(new Date())){
            throw new OtpException(OtpErrorMessage.OTP_EXP);
        }
        otp.setConfig(OtpConfig.LOGIN);
        return otp;
    }

    @Override
    public Otp checkAppOtp(User user, String code) {
        Totp totp = new Totp(user.getSecret());
        Otp otp = new Otp();
        otp.setOtp_type(OtpType.APP_OTP);
        otp.setStatus(StatusModel.DISABLE);
        otp.setConfig(OtpConfig.LOGIN);
        otp.setCode(code);
        otp.setCode_exp(new Date());
        otp.setUser(user);
        Otp otpSave = otpRepository.saveAndFlush(otp);
        if(totp.verify(code)){
            return OtpMapping.convert(otpSave);
        }else{
            return null;
        }
        return null;
    }

    @Override
    public OtpDto checkOtp(User name, String code, OtpType otpType, OtpConfig otpConfig) {
        return null;
    }



    @Override
    public OtpDto checkOtp(User user, String code, OtpType otpType) {
        if (otpType == OtpType.MAIL_OTP){

        }else{

        }

    }

    @Override
    public boolean sendCodeOtp(String uuid) {
        User user  = userService.searchUserByUuid(uuid);
        OtpDto code = generalMailOTP(user,6);
        if(code == null)
            throw new OtpException(OtpErrorMessage.CREATE_ERROR);
        boolean isSuccess = false;
        try {
            isSuccess = emailService
                    .sendEmailHtml(
                            user.getEmail()
                            ,"Kit502"
                            , Help.getResourceFileAsString("templates/email/email_code.html")
                                    .replace("${CODE}", code.getCode())
                                    .replace("${USERNAME}", user.getUsername())
                    );
        } catch (MessagingException e) {
            throw new OtpException(e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean checkCodeOtp(String uuid, String code, OtpType otpType) {
        User user = userService.searchUserByUuid(uuid);
        OtpDto otpDto = checkOtp(user, code, otpType);
        if(otpDto != null)
            return true;
        return false;
    }


    @Override
    public Date getExpOtp() {
        return new Date(new Date().getTime() + 180000);
    }

    @Override
    public String generalQR2fa(User user) throws UnsupportedEncodingException {
        return QR_PREFIX + URLEncoder.encode(String.format(
                        "otpauth://totp/%s:%s?secret=%s&issuer=%s",
                        APP_NAME, user.getEmail(), user.getSecret(), APP_NAME),
                "UTF-8");
    }
}
