package com.kit.chat_login.mapping;

import com.kit.chat_login.dto.RoleDto;
import com.kit.chat_login.model.authen.Role;

public class RoleMapping {
    public static RoleDto convert(Role role){
        return new RoleDto(
          role.getStatus(),
          role.getCreate_at(),
          role.getName(),
          role.getDescription(),
                PermissionMapping.convert(role.getPermissions())
        );
    }
}
