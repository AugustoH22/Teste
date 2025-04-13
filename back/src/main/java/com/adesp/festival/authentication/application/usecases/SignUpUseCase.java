package com.adesp.festival.authentication.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.authentication.application.services.InviteService;
import com.adesp.festival.authentication.application.services.SignUpService;
import com.adesp.festival.authentication.domain.entities.User;
import com.adesp.festival.authentication.domain.repositories.InviteRepository;
import com.adesp.festival.authentication.domain.repositories.UserRepository;

@UseCase
public class SignUpUseCase {

    private final InviteService inviteService;
    private final InviteRepository inviteRepository;
    private final SignUpService signUpService;
    private final UserRepository userRepository;

    public SignUpUseCase(InviteService inviteService, InviteRepository inviteRepository, SignUpService signUpService, UserRepository userRepository) {
        this.inviteService = inviteService;
        this.inviteRepository = inviteRepository;
        this.signUpService = signUpService;
        this.userRepository = userRepository;
    }

    public User execute(User user, String token){
        this.inviteService.validateInviteToken(token);
        this.inviteService.verifyIfInviteTokenWasUsed(token);
        this.signUpService.setInviteTokenClaimsInUser(user, token);
        this.signUpService.isEmailInUse(user.getEmail());
        this.signUpService.isUsernameInUse(user.getUsername());
        this.inviteService.inactivateInviteToken(token);
        String password = user.getPassword();
        user.setPassword(this.signUpService.encryptPassword(password));
        return this.userRepository.save(user);
    }
}
