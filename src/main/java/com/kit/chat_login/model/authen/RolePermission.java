package com.kit.chat_login.model.authen;


import com.kit.chat_login.model.User;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "role_permission")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission  implements Serializable {
    @Id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id")
    private Permission permission;
}
