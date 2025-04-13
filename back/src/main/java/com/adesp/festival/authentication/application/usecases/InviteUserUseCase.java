package com.adesp.festival.authentication.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.authentication.application.services.InviteService;
import com.adesp.festival.authentication.application.services.TokenService;
import com.adesp.festival.authentication.domain.entities.Invite;
import com.adesp.festival.authentication.domain.entities.User;
import com.adesp.festival.authentication.domain.enums.Roles;
import com.adesp.festival.authentication.domain.repositories.InviteRepository;
import com.adesp.festival.authentication.domain.repositories.UserRepository;
import com.adesp.festival.authentication.infrastructure.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.time.Duration;
import java.time.Instant;

@UseCase
public class InviteUserUseCase {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final InviteRepository inviteRepository;
    private final InviteService inviteService;

    @Value("${com.adesp.festival.invite-token-hours}")
    private Integer inviteTokenDuration;

    public InviteUserUseCase(TokenService tokenService, UserRepository userRepository, InviteRepository inviteRepository, InviteService inviteService) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.inviteRepository = inviteRepository;
        this.inviteService = inviteService;
    }

    public Invite execute(Long authorId, String email, Roles role){
        String inviteToken = this.tokenService.generateInviteToken(authorId.toString(), email, role);
        User storedUser = this.userRepository.findById(authorId).get();

        this.inviteService.isEmailRegistered(email);
        this.inviteService.sendInviteMail(storedUser, email, role, inviteToken);

        return this.inviteRepository.save(new Invite(
                email,
                inviteToken,
                Instant.now().plus(Duration.ofHours(inviteTokenDuration)),
                true,
                storedUser
        ));
    }
}
