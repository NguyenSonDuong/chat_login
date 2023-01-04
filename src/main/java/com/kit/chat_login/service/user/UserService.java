package com.kit.chat_login.service.user;

import com.kit.chat_login.dto.TokenDto;
import com.kit.chat_login.dto.UserDto;
import com.kit.chat_login.dto.UserInfoDto;
import com.kit.chat_login.model.User;
import com.kit.chat_login.model.token.Token;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public interface UserService {
    TokenDto login(String username, String password);
    TokenDto register(String username, String email, String password);
    boolean changePassword(String oldPassword, String newPassword);
    User searchUserById(int id);
    User searchUserByUuid(String uuid);
    Page<UserDto> searchUser(String username,Pageable pageable);
    UserDto activeUser(String uuid);
    UserDto disableUser(String uuid);
    UserDto lockUser(String uuid);
    UserDto addRole(String uuid,String name);


}
