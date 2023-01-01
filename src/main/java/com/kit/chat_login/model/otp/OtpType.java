package com.kit.chat_login.model.otp;

import com.kit.chat_login.model.BaseEntity;
import com.kit.chat_login.model.User;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public enum OtpType {
    MAIL_OTP,
    APP_OTP
}
