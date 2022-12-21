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

@Entity
@Table(name = "otp_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtpType extends BaseEntity {
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "description", length = 100)
    private String description;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "otp_type")
    private List<Otp> otps ;


}
