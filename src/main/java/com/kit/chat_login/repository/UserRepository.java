package com.kit.chat_login.repository;

import com.kit.chat_login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}