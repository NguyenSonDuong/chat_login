package com.kit.chat_login.repository.otp;

import com.kit.chat_login.model.otp.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
}