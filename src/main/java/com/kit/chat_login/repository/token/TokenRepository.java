package com.kit.chat_login.repository.token;

import com.kit.chat_login.model.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {
}