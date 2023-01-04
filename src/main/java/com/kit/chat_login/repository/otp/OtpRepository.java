package com.kit.chat_login.repository.otp;

import com.kit.chat_login.dto.OtpDto;
import com.kit.chat_login.model.StatusModel;
import com.kit.chat_login.model.User;
import com.kit.chat_login.model.otp.Otp;
import com.kit.chat_login.model.otp.OtpConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OtpRepository extends JpaRepository<Otp, Integer> {

    @Query("select u from Otp u where u.user = ?1 and u.config = 2 and u.otp_type = 0 order by u.create_at desc ")
    Page<Otp> findOtpNewCreate(User user, Pageable pageable);

    Otp findByUser(User user);
    Otp findByUserAndCode(User user, String code);
    Otp findByUserAndCodeAndConfigAndStatus(User user, String code, OtpConfig config, StatusModel statusModel);
}