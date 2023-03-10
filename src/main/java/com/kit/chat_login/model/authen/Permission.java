package com.kit.chat_login.model.authen;

import com.kit.chat_login.model.BaseEntity;
import javax.persistence.*;

import com.kit.chat_login.model.StatusModel;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "permisstion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends BaseEntity {

    public Permission(String name, String description,StatusModel statusModel){
        super(statusModel);
        this.description = description;
        this.name = name;
    }

    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "description", length = 100, nullable = true)
    private String description;



}
