package com.kit.chat_login.dto;

import com.kit.chat_login.model.StatusModel;
import com.kit.chat_login.model.authen.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * A DTO for the {@link Role} entity
 */
@Data
public class RoleDto implements Serializable {
    private final StatusModel status;
    private final Date create_at;
    private final String name;
    private final String description;
    private final Set<PermissionDto> permissionDtos;
}