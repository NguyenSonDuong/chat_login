package com.kit.chat_login.advice_controller;

import com.kit.chat_login.exception.UserException;
import com.kit.chat_login.help.Help;
import com.kit.chat_login.responsive.Base;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UserException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> userExceptionHandler(UserException ex, WebRequest request) {
        return Help.Error(ex.getMessage(),null);
    }
}
