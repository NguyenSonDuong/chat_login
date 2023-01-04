package com.kit.chat_login.service.email;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface EmailService {
    boolean sendEmail(String to, String subject, String text);
    boolean sendEmailHtml(String to, String subject, String text) throws MessagingException;
}
