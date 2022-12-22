package com.kit.chat_login.repository.otp;

import com.kit.chat_login.model.otp.OtpType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpTypeRepository extends JpaRepository<OtpType, Integer> {
}