package com.kit.chat_login.repository.authen;

import com.kit.chat_login.model.authen.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}