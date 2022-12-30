package com.kit.chat_login.repository.authen;

import com.kit.chat_login.model.authen.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Page<Permission> findByNameLike(String name, Pageable pageable);
    Permission findBy_id(int _id);
}