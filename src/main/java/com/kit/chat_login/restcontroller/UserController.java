package com.kit.chat_login.restcontroller;

import com.kit.chat_login.dto.UserDto;
import com.kit.chat_login.exception.UserException;
import com.kit.chat_login.help.Help;
import com.kit.chat_login.message.user.UserErrorMessage;
import com.kit.chat_login.model.token.Token;
import com.kit.chat_login.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password){
        Token token = userService.login(username, password);
        if(token == null)
            throw new UserException(UserErrorMessage.LOGIN_ERROR);
        return Help.Success("Login success", token);
    }

    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> register(
            @RequestParam(name = "username")
            String username,
            @RequestParam(name = "email")
            String email,
            @RequestParam(name = "password")
            String password ){
        UserDto userDto = userService.register(username,email,password);
        if(userDto == null)
            throw new UserException(UserErrorMessage.LOGIN_ERROR);
        return Help.Success("Register success", userDto);
    }

    @GetMapping(value = "/check-roller")
    @PreAuthorize("hasAnyAuthority('ADMIN_CREATE')")
    public String testRoler(){
        return "Success";
    }
}
