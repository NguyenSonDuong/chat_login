package com.kit.chat_login.service.token;

import com.kit.chat_login.dto.TokenDto;
import com.kit.chat_login.exception.UserException;
import com.kit.chat_login.mapping.TokenMapping;
import com.kit.chat_login.mapping.UserMapping;
import com.kit.chat_login.message.user.UserErrorMessage;
import com.kit.chat_login.model.StatusModel;
import com.kit.chat_login.model.User;
import com.kit.chat_login.model.token.Token;
import com.kit.chat_login.repository.token.TokenRepository;
import com.kit.chat_login.security.jwt.JwtUtil;
import com.kit.chat_login.security.jwt.userdetail.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenServiceImp implements TokenService{

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    TokenRepository tokenRepository;

    @Override
    public TokenDto createToken(User user) {
        UserDetailsImp userPrincipal = UserMapping.convertUserDetailsImp(user);
        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));
        token.setToken_exp(jwtUtil.generateExpirationDate());
        token.setStatus(StatusModel.ACTIVE);
        token.setUsers(user);
        Token token1 = tokenRepository.save(token);
        if(token1 == null)
            throw new UserException(UserErrorMessage.TOKEN_NOT_SAVE);
        return TokenMapping.convert(token1);
    }
}
