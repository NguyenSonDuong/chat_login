package com.kit.chat_login.service.user;

import com.kit.chat_login.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public interface UserService {
    UserDto login(String username, String password);
    UserDto register(String username, String email, String password);
    boolean changePassword(String oldPassword, String newPassword);

    List<UserDto> searchUser();

    Page<UserDto> getAll(Pageable pageable);

}
