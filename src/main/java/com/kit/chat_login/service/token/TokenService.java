package com.kit.chat_login.service.token;

import com.kit.chat_login.dto.TokenDto;
import com.kit.chat_login.model.User;
import com.kit.chat_login.model.token.Token;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    TokenDto createToken(User user);

}
