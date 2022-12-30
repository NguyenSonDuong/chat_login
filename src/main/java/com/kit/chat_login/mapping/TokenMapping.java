package com.kit.chat_login.mapping;

import com.kit.chat_login.dto.RoleDto;
import com.kit.chat_login.dto.TokenDto;
import com.kit.chat_login.model.authen.Role;
import com.kit.chat_login.model.token.Token;

public class TokenMapping {
    public static TokenDto convert(Token token){
        return new TokenDto(
                token.getStatus(),
                token.getCreate_at(),
                token.getToken(),
                token.getToken_exp()
        );
    }
}
