package com.kit.chat_login.repository.userinfo;

import com.kit.chat_login.model.user.UserInfo;
import com.kit.chat_login.model.user.hobby.UserHobby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

}