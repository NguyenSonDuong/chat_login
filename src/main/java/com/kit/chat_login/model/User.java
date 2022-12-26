package com.kit.chat_login.model;


import com.kit.chat_login.model.authen.Role;
import com.kit.chat_login.model.authen.UserRole;
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
@Table(name = "user")
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


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    private UserInfo userInfo = new UserInfo();

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "users")
    private List<Token> tokens = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Otp> otps = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserRole> userRoles = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
