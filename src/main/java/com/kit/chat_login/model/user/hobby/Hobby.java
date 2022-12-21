package com.kit.chat_login.model.user.hobby;

import com.kit.chat_login.model.BaseEntity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hobby")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hobby extends BaseEntity {
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "description", length = 100, nullable = true)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hobby")
    List<UserHobby> userHobbies ;
}
