package com.kit.chat_login.mapping;

import com.kit.chat_login.dto.PermissionDto;
import com.kit.chat_login.model.authen.Permission;

import java.util.HashSet;
import java.util.Set;

public class PermissionMapping {
    public static PermissionDto convert(Permission permission){
        return new PermissionDto(
          permission.getStatus(),
          permission.getCreate_at(),
            permission.getName(),
            permission.getDescription()
        );
    }
    public static Set<PermissionDto> convert(Set<Permission> permissions){
        Set<PermissionDto> listPermission = new HashSet<>();
        for (Permission per :
                permissions) {
            listPermission.add(convert(per));
        }
        return listPermission;
    }
}
