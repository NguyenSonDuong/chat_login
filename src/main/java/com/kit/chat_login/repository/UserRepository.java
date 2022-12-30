package com.kit.chat_login.repository;

import com.kit.chat_login.dto.UserDto;
import com.kit.chat_login.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByUsername(String username);
    boolean existsByUsernameOrEmail(String username, String email);

}