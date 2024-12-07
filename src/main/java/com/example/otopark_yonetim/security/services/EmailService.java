package com.example.otopark_yonetim.security.services;

import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    private static EmailService instance;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public static EmailService getInstance(JavaMailSender mailSender) {
        if (instance == null) {
            synchronized (EmailService.class) {
                if (instance == null) {
                    instance = new EmailService(mailSender);
                }
            }
        }
        return instance;
    }

    public String sendVerificationCode(String to) {
        String verificationCode = generateVerificationCode();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Doğrulama Kodu");
        message.setText("Doğrulama Kodunuz: " + verificationCode);

        try {
            mailSender.send(message);
        } catch (Exception e) {
            return null;
        }

        return verificationCode;
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // A random 6 digit number
        return String.valueOf(code);
    }
}
