package com.kit.chat_login.model.user.setting;

import com.kit.chat_login.model.BaseEntity;
import com.kit.chat_login.model.user.UserInfo;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "setting")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Setting extends BaseEntity {
    @Column(name = "theme_style")
    private int theme_style;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    private UserInfo user_info;
}
