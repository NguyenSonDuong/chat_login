package com.kit.chat_login.repository.authen;

import com.kit.chat_login.model.authen.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
    Page<Role> findByNameLike(String name, Pageable pageable);
    Role findBy_id(int _id);
}