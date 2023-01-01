package com.kit.chat_login.model;


import com.kit.chat_login.model.authen.Role;
import com.kit.chat_login.model.otp.Otp;
import com.kit.chat_login.model.token.Token;
import com.kit.chat_login.model.user.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user",
indexes = {
        @Index(name = "uuid_index",columnList ="uuid"),
        @Index(name = "username_index",columnList ="username"),
        @Index(name = "email_index",columnList ="email")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    @Column(name = "uuid", length = 50, nullable = false)
    private String uuid = UUID.randomUUID().toString();
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    @Column(name = "username", length = 100, nullable = false)
    private String username;
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    @Column(name = "time_disable")
    private Date time_disable;
    @Column(name = "2fa_enable")
    private TwoFAStatus _2fa_enable;
    @Column(name = "secret")
    private String secret;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user", orphanRemoval = true)
    private UserInfo userInfo = new UserInfo();

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Token> tokens;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Set<Otp> otps;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
