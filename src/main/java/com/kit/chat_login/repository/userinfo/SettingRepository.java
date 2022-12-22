package com.kit.chat_login.repository.userinfo;

import com.kit.chat_login.model.user.setting.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting, Integer> {
}