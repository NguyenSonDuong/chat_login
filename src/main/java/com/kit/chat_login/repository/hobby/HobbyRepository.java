package com.kit.chat_login.repository.hobby;

import com.kit.chat_login.model.user.hobby.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Integer> {
}