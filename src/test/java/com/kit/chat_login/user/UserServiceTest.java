package com.kit.chat_login.user;

import com.kit.chat_login.dto.UserDto;
import com.kit.chat_login.model.token.Token;
import com.kit.chat_login.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void registerTest(){
        UserDto userDto = userService.register("nguyenduong08041999","hunterdemon9x99@gmail.com","nguyenduong123");
        assertThat(userDto).isNotNull();
    }

    @Test
    public void loginTest(){
        Token token = userService.login("nguyenduong08041999","nguyenduong123");
        System.out.println(token);
        assertThat(token).isNotNull();
    }
}
