package com.kit.chat_login.mapping;

import com.kit.chat_login.dto.UserDto;
import com.kit.chat_login.model.User;

public class UserMapping {
    public static UserDto convert(User user){
        return new UserDto(
                user.getStatus(),
                user.getCreate_at(),
                user.getUuid(),
                user.getEmail(),
                user.getUsername(),
                user.getTime_disable(),
                UserInfoMapping.convert(user.getUserInfo())
        );
    }
}
