package com.kit.chat_login.model.otp;

import com.kit.chat_login.model.BaseEntity;
import com.kit.chat_login.model.User;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "otp")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Otp extends BaseEntity  {
    @Column(name = "code", length = 5,nullable = false)
    private String code;
    @Column(name = "code_exp", nullable = false)
    private Date code_exp;
    @Column(name = "config")
    private OtpConfig config;

    @Column(name = "otp_type")
    private OtpType otp_type;


    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
