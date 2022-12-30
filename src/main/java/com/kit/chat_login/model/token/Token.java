package com.kit.chat_login.model.token;

import com.kit.chat_login.model.BaseEntity;
import com.kit.chat_login.model.User;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token extends BaseEntity {
    @Column(name = "token", length = 500,nullable = false)
    private String token;
    @Column(name = "token_exp")
    private Date token_exp;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User users;

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", token_exp=" + token_exp +
                '}';
    }
}
