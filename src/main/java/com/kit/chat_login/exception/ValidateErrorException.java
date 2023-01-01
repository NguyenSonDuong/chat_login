package com.kit.chat_login.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidateErrorException extends RuntimeException{
    private Object content;
    public ValidateErrorException(String message) {
        super(message);
    }

    public ValidateErrorException(String message, Object content) {
        super(message);
        this.content = content;
    }
}
