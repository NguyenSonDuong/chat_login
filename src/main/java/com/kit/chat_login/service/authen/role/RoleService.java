package com.kit.chat_login.service.authen.role;

import com.kit.chat_login.dto.PermissionDto;
import com.kit.chat_login.dto.RoleDto;
import com.kit.chat_login.model.authen.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    RoleDto createRole(String name, String description);
    RoleDto updateRole(int id, String name, String description);
    boolean deleteRole(int id);
    RoleDto readRole(int id);
    Role readRole(String name);
    Page<RoleDto> readRole(String name,Pageable pageable);
    Page<RoleDto> readAllRole(Pageable pageable);
}
