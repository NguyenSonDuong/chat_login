package com.kit.chat_login.repository.hobby;

import com.kit.chat_login.model.user.hobby.Hobby;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HobbyRepository extends JpaRepository<Hobby, Integer> {
    Page<Hobby> findByNameLike(String name, Pageable pageable);
    Hobby findBy_id(int _id);
}