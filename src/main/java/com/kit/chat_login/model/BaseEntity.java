package com.kit.chat_login.model;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    public BaseEntity(StatusModel status) {
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id", nullable = false)
    private int _id;

    @Column(name = "status")
    private StatusModel status;

    @Column(name = "create_at")
    private Date create_at;

    @Column(name = "update_at")
    private Date update_at;

    @PrePersist
    public void onPrePersist(){
        create_at = new Date();
        update_at = new Date();
    }

    @PreUpdate
    private void onPreUpdate(){
        update_at = new Date();
    }
}
