package com.kit.chat_login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class UserController {

    @GetMapping("/abc")
    public String testEmail(){
        return "email/email_code.html";
    }
}
