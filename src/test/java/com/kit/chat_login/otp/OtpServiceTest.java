package com.kit.chat_login.otp;

import com.kit.chat_login.dto.OtpDto;
import com.kit.chat_login.model.otp.OtpType;
import com.kit.chat_login.service.otp.OtpService;
import com.kit.chat_login.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class OtpServiceTest {

    @Autowired
    OtpService otpService;

    @Test
    public void generalOtpTest(){

        OtpDto otp = otpService.generalMailOTP(2, 5, OtpType.APP_OTP);
        assertThat(otp).isNotNull();
    }
}
