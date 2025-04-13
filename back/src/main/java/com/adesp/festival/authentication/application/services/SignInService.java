package com.adesp.festival.authentication.application.services;

import com.adesp.festival.authentication.domain.entities.User;
import com.adesp.festival.authentication.domain.exceptions.BadCredentialsException;
import com.adesp.festival.authentication.domain.exceptions.InactiveUserException;
import com.adesp.festival.authentication.domain.exceptions.UnavailableEmailException;
import com.adesp.festival.authentication.domain.exceptions.UnavailableUsernameException;
import com.adesp.festival.authentication.domain.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SignInService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignInService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void verifyUserStatus(User stored){
        if(stored.getActive() == false){
            throw new InactiveUserException();
        }
    }

    @Transactional(readOnly = true)
    public User authenticate(String login, String password){
        User stored = this.userRepository.findByUsernameOrEmail(login)
                .orElseThrow(() -> new BadCredentialsException());

        boolean passwordMatches = this.passwordEncoder.matches(password, stored.getPassword());

        if(!passwordMatches){
            throw new BadCredentialsException();
        }

        this.verifyUserStatus(stored);

        return stored;
    }
}
