package com.kit.chat_login.repository.userinfo;

import com.kit.chat_login.model.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

}