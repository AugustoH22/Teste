package com.adesp.festival.authentication.application.services;

import com.adesp.festival.authentication.domain.entities.User;
import com.adesp.festival.authentication.domain.enums.Roles;
import com.adesp.festival.authentication.domain.exceptions.UnavailableEmailException;
import com.adesp.festival.authentication.domain.exceptions.UnavailableUsernameException;
import com.adesp.festival.authentication.domain.repositories.UserRepository;
import com.auth0.jwt.JWT;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public void isEmailInUse(String email){
        Optional<User> stored = this.userRepository.findByEmail(email);

        if(stored.isPresent()){
            throw new UnavailableEmailException();
        }
    }

    @Transactional(readOnly = true)
    public void isUsernameInUse(String username){
        Optional<User> stored = this.userRepository.findByUsername(username);

        if(stored.isPresent()){
            throw new UnavailableUsernameException();
        }
    }

    public String getInviteTokenClaimContent(String token, String claim){
        return JWT.decode(token).getClaim(claim).asString();
    }

    public User setInviteTokenClaimsInUser(User user, String token){
        user.setEmail(this.getInviteTokenClaimContent(token, "email"));
        user.setRole(Roles.valueOf(this.getInviteTokenClaimContent(token, "role")));
        return user;
    }

    public String encryptPassword(String password){
        return this.passwordEncoder.encode(password);
    }
}
