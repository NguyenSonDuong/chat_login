package com.kit.chat_login.dto;

import com.kit.chat_login.model.StatusModel;
import com.kit.chat_login.model.otp.OtpConfig;
import com.kit.chat_login.model.otp.OtpType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.kit.chat_login.model.otp.Otp} entity
 */
@Data
public class OtpDto implements Serializable {
    private final StatusModel status;
    private final Date create_at;
    private final String code;
    private final String code_exp;
    private final OtpConfig congfirm;
    private final OtpType otp_type;
    private final Date otp_exp;
}