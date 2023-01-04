package com.kit.chat_login.api;

import com.kit.chat_login.dto.TokenDto;
import com.kit.chat_login.dto.UserDto;
import com.kit.chat_login.exception.UserException;
import com.kit.chat_login.help.Help;
import com.kit.chat_login.message.user.UserErrorMessage;
import com.kit.chat_login.model.otp.OtpType;
import com.kit.chat_login.request.UserRequest;
import com.kit.chat_login.security.jwt.userdetail.UserDetailsImp;
import com.kit.chat_login.service.email.EmailService;
import com.kit.chat_login.service.otp.OtpService;
import com.kit.chat_login.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OtpService otpService;



    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Valid UserRequest.Login login){
        TokenDto token = userService.login(login.getUsername(), login.getPassword());
        if(token == null)
            throw new UserException(UserErrorMessage.LOGIN_ERROR);
        return Help.Success("Login success", token);
    }

    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> register(@Valid UserRequest.Register register){
        TokenDto tokenDto = userService.register(register.getUsername(),register.getEmail(),register.getPassword());
        if(tokenDto == null)
            throw new UserException(UserErrorMessage.LOGIN_ERROR);
        boolean isCheck = otpService.sendCodeOtp(tokenDto.getUuid_user());
        if(!isCheck)
            return Help.Error("Send code error",null);
        return Help.Success("Register success", tokenDto);
    }

    @GetMapping(value = "/send-otp")
    @PreAuthorize("hasAnyAuthority('REGISTER_VETIFY')")
    public String sendOtp(){
        UserDetailsImp userDetailsImp = (UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetailsImp == null)
            throw new AuthorizationServiceException("ERROR");
        if(otpService.sendCodeOtp(userDetailsImp.getUuid()))
            return "Success";
        return "Error";
    }

    @PostMapping(value = "/check-otp")
    @PreAuthorize("hasAnyAuthority('REGISTER_VETIFY')")
    public String checkOtpRegister(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "otp_type") int otpType
            ){
        UserDetailsImp userDetailsImp = (UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(otpService.checkCodeOtp(userDetailsImp.getUuid(),code, OtpType.MAIL_OTP)){
            userService.activeUser(userDetailsImp.getUuid());
            return "Success";
        }
        return "Error";
    }

}
//dậy rùi hử bé em khong ngu
// ù ù đỡ mệt chưa á
//em khong met dau dang nau com doi anh ve
//chờ a chút nhá sắp đk về rồi okiii iu anh
// yeu bé nhiều <3