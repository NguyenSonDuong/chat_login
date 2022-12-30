package com.kit.chat_login.dto;

import com.kit.chat_login.model.StatusModel;
import com.kit.chat_login.model.authen.Permission;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * A DTO for the {@link Permission} entity
 */
@Data
public class PermissionDto implements Serializable {
    private final StatusModel status;
    private final Date create_at;
    private final String name;
    private final String description;
}