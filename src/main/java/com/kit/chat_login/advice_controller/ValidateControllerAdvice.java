package com.kit.chat_login.advice_controller;

import com.kit.chat_login.exception.UserException;
import com.kit.chat_login.exception.ValidateErrorException;
import com.kit.chat_login.help.Help;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.xml.bind.ValidationException;
import java.util.HashSet;
import java.util.Set;


@RestControllerAdvice
public class ValidateControllerAdvice {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> userExceptionHandler(BindException ex, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        Set<String> error = new HashSet<>();
        bindingResult.getFieldErrors().forEach(r -> {
            error.add(r.getDefaultMessage());
        });
        return Help.Error("Data is missing",error);
    }
}