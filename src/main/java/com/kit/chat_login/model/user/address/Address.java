package com.kit.chat_login.model.user.address;

import com.kit.chat_login.model.BaseEntity;
import com.kit.chat_login.model.user.UserInfo;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity {
    @Column(name = "nation", length = 20, nullable = false)
    private String nation;
    @Column(name = "city", length = 50)
    private String city;
    @Column(name = "state", length = 100)
    private String state;
    @Column(name = "address_one", length = 100)
    private String address_one;
    @Column(name = "address_two", length = 100)
    private String address_two;

    @OneToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo user_info;

}
