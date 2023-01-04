package com.kit.chat_login.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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

    @Override
    public boolean sendEmailHtml(String to, String subject, String text) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setSubject(subject);
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, true);
        helper.setFrom(emailfrom);
        helper.setTo(to);
        helper.setText(text, true);
        javaMailSender.send(message);
        return false;
    }
}
