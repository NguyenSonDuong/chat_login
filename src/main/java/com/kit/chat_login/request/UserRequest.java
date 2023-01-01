package com.kit.chat_login.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequest {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Login{
        @NotBlank(message = "Username is required")
        @Size(min = 10, max = 100, message = "Username is wrong format")
        String username;

        @NotBlank(message = "Password is required")
        @Size(min = 10, max = 100, message = "Password is wrong format")
        String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Register{
        @NotBlank(message = "Username is required")
        @Size(min = 10, max = 100, message = "Username is wrong format")
        private String username;

        @NotBlank(message = "Email is required")
        @Email(message = "Email is wrong format of email")
        @Size(min = 10, max = 100, message = "Email is wrong format")
        private String email;

        @NotBlank(message = "Password is required")
        @Size(min = 10, max = 100, message = "Password is wrong format")
        private String password;
    }
}
