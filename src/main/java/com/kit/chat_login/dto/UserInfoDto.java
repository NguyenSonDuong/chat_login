package com.kit.chat_login.dto;

import com.kit.chat_login.model.StatusModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * A DTO for the {@link com.kit.chat_login.model.user.UserInfo} entity
 */
@Data
@Getter
@Setter
@AllArgsConstructor
public class UserInfoDto implements Serializable {
    private final StatusModel status;
    private final String fullname;
    private final String avatar;
    private final Date birthday;
    private final int sex;
    private final Set<HobbyDto> hobby;
    private final AddressDto address;
    private final SettingDto setting;
}