package com.kit.chat_login.dto;

import com.kit.chat_login.model.StatusModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.kit.chat_login.model.token.Token} entity
 */
@Data
public class TokenDto implements Serializable {
    private final StatusModel status;
    private final Date create_at;
    private final String token;
    private final Date token_exp;
    private final String uuid_user;
}