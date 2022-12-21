package com.kit.chat_login.model.user.hobby;


import com.kit.chat_login.model.authen.Role;
import com.kit.chat_login.model.user.UserInfo;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "user_hobby")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserHobby  implements Serializable {
    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    private UserInfo user;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "hobby_id")
    private Hobby hobby;
}
