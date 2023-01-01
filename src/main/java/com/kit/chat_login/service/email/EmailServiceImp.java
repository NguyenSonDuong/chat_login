package com.kit.chat_login.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImp implements EmailService{

    @Value("${spring.mail.username}")
    private String emailfrom;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean sendEmail(String to, String subject, String text) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailfrom);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
        }catch (Exception ex){
            return false;
        }
        return true;
    }
}
