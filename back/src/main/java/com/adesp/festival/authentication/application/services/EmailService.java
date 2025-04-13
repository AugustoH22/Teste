package com.adesp.festival.authentication.application.services;

import com.adesp.festival.authentication.domain.entities.User;
import com.adesp.festival.authentication.domain.enums.Roles;
import com.adesp.festival.authentication.domain.exceptions.RegisteredEmailException;
import com.adesp.festival.authentication.domain.repositories.UserRepository;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailService {

    private final UserRepository userRepository;
    private final MailSender mailSender;

    public EmailService(UserRepository userRepository, MailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    public void sendMail(String destinationEmail, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinationEmail);
        message.setSubject(subject);
        message.setText(text);
        try{
            this.mailSender.send(message);
        } catch (MailException e) {
            throw new RuntimeException(e);
        }
    }
}
