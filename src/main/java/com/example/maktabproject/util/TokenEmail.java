package com.example.maktabproject.util;

import com.example.maktabproject.model.User;
import com.example.maktabproject.model.VerificationToken;
import com.example.maktabproject.service.Impl.VerificationTokenServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TokenEmail {

    private final VerificationTokenServiceImpl verificationTokenService;

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(User user) {
        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .expiryDate(calculateExpiryDate(60))
                .build();
        verificationTokenService.register(verificationToken);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = "http://localhost:8080" + "/user/verify?token=" + token;
        String message = "click this";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + confirmationUrl);
        mailSender.send(email);
    }

    public LocalDateTime calculateExpiryDate(int expiryTimeInMinutes) {
        return LocalDateTime.now().plusMinutes(expiryTimeInMinutes);
    }
}