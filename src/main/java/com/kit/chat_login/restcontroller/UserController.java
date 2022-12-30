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

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam
            @NotBlank(message = "Username is required")
            @Size(min = 10, max = 100, message = "Username is wrong format")
            String username,

            @RequestParam
            @NotBlank(message = "Password is required")
            @Size(min = 10, max = 100, message = "Password is wrong format")
            String password ){
        Token token = userService.login(username, password);
        if(token == null)
            throw new UserException(UserErrorMessage.LOGIN_ERROR);
        return Help.Success("Login success", token);
    }

    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> register(
            @RequestParam(name = "username")
            @NotBlank(message = "Username is required")
            @Size(min = 10, max = 100, message = "Username is wrong format")
            String username,

            @RequestParam(name = "email")
            @Email(message = "Email is wrong format")
            @NotBlank(message = "Email is required")
            @Size(min = 10, max = 100, message = "Email is wrong format")
            String email,

            @RequestParam(name = "password")
            @NotBlank(message = "Password is required")
            @Size(min = 10, max = 100, message = "Password is wrong format")
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
//dậy rùi hử bé em khong ngu
// ù ù đỡ mệt chưa á
//em khong met dau dang nau com doi anh ve
//chờ a chút nhá sắp đk về rồi okiii iu anh
// yeu bé nhiều <3