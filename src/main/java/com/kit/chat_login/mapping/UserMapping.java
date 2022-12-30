package com.kit.chat_login.mapping;

import com.kit.chat_login.dto.UserDto;
import com.kit.chat_login.exception.UserException;
import com.kit.chat_login.message.user.UserErrorMessage;
import com.kit.chat_login.model.User;
import com.kit.chat_login.security.jwt.userdetail.UserDetailsImp;

import java.util.HashSet;
import java.util.Set;

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

    public static UserDetailsImp convertUserDetailsImp(User user){
        if(user == null)
            throw new UserException(UserErrorMessage.USER_NOT_EXITS);
        UserDetailsImp userDetailsImp = new UserDetailsImp();
        userDetailsImp.setStatus(user.getStatus());
        userDetailsImp.setUsername(user.getUsername());
        userDetailsImp.setEmail(user.getEmail());
        userDetailsImp.setPassword(user.getPassword());
        userDetailsImp.setUuid(user.getUuid());

        Set<String> authorities = new HashSet<>();
        if(user.getRoles() !=null){
            user.getRoles().forEach( r ->{
                authorities.add(r.getName());
                r.getPermissions().forEach(r2 -> {
                    authorities.add(r2.getName());
                });
            });
        }
        userDetailsImp.setAuthorities(authorities);
        return userDetailsImp;
    }
}
