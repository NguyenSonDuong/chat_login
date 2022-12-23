package com.kit.chat_login.dto;

import com.kit.chat_login.model.StatusModel;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.kit.chat_login.model.User} entity
 */
@Data
@Getter
@Setter
@AllArgsConstructor
public class UserDto implements Serializable {
    private final StatusModel status;
    private final Date create_at;
    private final String uuid;
    private final String email;
    private final String username;
    private final Date time_disable;
    private final UserInfoDto userInfo;
}