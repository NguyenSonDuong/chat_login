package com.kit.chat_login.model.user;

import com.kit.chat_login.model.BaseEntity;
import com.kit.chat_login.model.User;
import com.kit.chat_login.model.user.address.Address;
import com.kit.chat_login.model.user.hobby.Hobby;
import com.kit.chat_login.model.user.hobby.UserHobby;
import com.kit.chat_login.model.user.setting.Setting;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "user_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends BaseEntity {

    @Column(name = "fullname", length = 100)
    private String fullname;

    @Column(name = "avatar", length = 250)
    private String avatar;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "sex")
    private int sex;

    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinTable(name = "user_hobby",
            joinColumns = @JoinColumn(name = "user_info_id"),
            inverseJoinColumns = @JoinColumn(name = "hobby_id")
    )
    private Set<Hobby> hobby ;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user_info")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "user_info")
    private Setting setting;

}
