package com.kit.chat_login.service.email;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    boolean sendEmail(String to, String subject, String text);
}
