package com.github.java3wro.project.user.service.impl;

import com.github.java3wro.project.user.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.password}")
    private String password;

    @Override
    public void sendEmail(String to, String token) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setReplyTo("java3wro@gmail.com");
            helper.setFrom("java3wro@gmail.com");
            helper.setSubject("BADUGI REGISTRATION");
            helper.setText("http://localhost:8989/#/confirm/" + token, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mail);
    }

    @Override
    public void passwordRecovery(String to, String content) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setReplyTo("java3wro@gmail.com");
            helper.setFrom("java3wro@gmail.com");
            helper.setSubject("PASSWORD RECOVERY TOKEN");
            helper.setText("localhost:8989/#/reset-password/" + content, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mail);
    }


}