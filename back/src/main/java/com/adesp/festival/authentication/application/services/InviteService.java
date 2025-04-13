package com.adesp.festival.authentication.application.services;

import com.adesp.festival.authentication.domain.entities.Invite;
import com.adesp.festival.authentication.domain.entities.User;
import com.adesp.festival.authentication.domain.enums.Roles;
import com.adesp.festival.authentication.domain.exceptions.ExpiredInviteException;
import com.adesp.festival.authentication.domain.exceptions.InvalidInviteException;
import com.adesp.festival.authentication.domain.exceptions.RegisteredEmailException;
import com.adesp.festival.authentication.domain.repositories.InviteRepository;
import com.adesp.festival.authentication.domain.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InviteService {

    private final EmailService emailService;
    private final InviteRepository inviteRepository;
    private final UserRepository userRepository;
    @Value("${com.adesp.festival.invite-token-secret}")
    private String inviteTokenSecret;

    public InviteService(EmailService emailService, InviteRepository inviteRepository, UserRepository userRepository) {
        this.emailService = emailService;
        this.inviteRepository = inviteRepository;
        this.userRepository = userRepository;
    }

    private void verifyTokenExpiration(String token, Algorithm algorithm){
        Date tokenExpiration = JWT.require(algorithm).build().verify(token).getExpiresAt();

        if(tokenExpiration.before(Date.from(Instant.now()))){
            throw new ExpiredInviteException();
        }
    }

    public void verifyIfInviteTokenWasUsed(String inviteToken){
        Invite invite = this.inviteRepository.findByInviteToken(inviteToken)
                .orElseThrow(() -> new InvalidInviteException());

        if(!invite.getActive()){
            throw new InvalidInviteException();
        }
    }

    public void inactivateInviteToken(String token){
        Invite invite = this.inviteRepository.findByInviteToken(token)
                .map(storedInvite -> {
                    storedInvite.setActive(false);
                    return storedInvite;
                })
                .orElseThrow(() -> new ExpiredInviteException());
    }

    public String validateInviteToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(inviteTokenSecret);
            this.verifyTokenExpiration(token, algorithm);
            return JWT.require(algorithm).build().verify(token).getSubject();
        } catch (UnsupportedEncodingException e) {
            throw new InvalidInviteException();
        }
    }

    private String formatRole(Roles role){
        if(role.name() == "ROLE_ADMIN"){
            return "administrador";
        }
        else if(role.name() == "ROLE_MUSICAL_JUDGE"){
            return "jurado musical";
        }

        return "jurado culinário";
    }

    public void isEmailRegistered(String email){
        Optional<User> storedUser = this.userRepository.findByEmail(email);

        if(storedUser.isPresent()){
            throw new RegisteredEmailException();
        }
    }

    public void sendInviteMail(User user, String email, Roles role, String inviteToken){
        this.isEmailRegistered(email);
        String emailTitle = String.format("Grande Notícia! Você foi convidado para ser %s no Festival Cultural de Paracatu!", this.formatRole(role));
        String emailText = String.format("""
                Olá!
                Esperamos que esteja muito bem.
                
                %s te convidou para participar como %s no Festival Cultural de Paracatu!
                
                Clique no link para criar a sua conta: http://localhost:8080/api/v1/signup?token=%s
                
                O Link tem duração de 2 horas!
                """, user.getName(), this.formatRole(role), inviteToken);
        this.emailService.sendMail(email, emailTitle, emailText);
    }
}
