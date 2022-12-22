package com.kit.chat_login.repository.authen;

import com.kit.chat_login.model.authen.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}