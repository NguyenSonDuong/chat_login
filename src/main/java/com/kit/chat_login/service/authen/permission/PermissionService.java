package com.kit.chat_login.service.authen.permission;

import com.kit.chat_login.dto.PermissionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PermissionService {
    PermissionDto createPermisstion(String name, String description);
    PermissionDto updatePermisstion(int id, String name, String description);
    boolean deletePermisstion(int id);
    PermissionDto readPermisstion(int id);
    Page<PermissionDto> readPermisstion(String name,Pageable pageable);
    Page<PermissionDto> readAllPermisstion(Pageable pageable);
}
