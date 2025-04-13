package com.adesp.festival.authentication.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.authentication.application.services.EmailService;
import com.adesp.festival.authentication.application.services.TokenService;
import com.adesp.festival.authentication.domain.entities.User;
import com.adesp.festival.authentication.domain.exceptions.UnavailableEmailException;
import com.adesp.festival.authentication.domain.repositories.UserRepository;

@UseCase
public class RequestPasswordRecoveryUseCase {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final TokenService tokenService;

    public RequestPasswordRecoveryUseCase(UserRepository userRepository, EmailService emailService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.tokenService = tokenService;
    }

    public void execute(String email){
        User storedUser = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UnavailableEmailException());

        this.emailService.sendMail(
                email,
                "Recuperação de Senha - Festival Cultural de Paracatu",
                String.format("""
                     Olá!
                     
                     Vimos que você solicitou a recuperação de sua senha. Para realizá-la acesse o link abaixo:
                     
                     http://localhost:8080/v1/api/auth/recovery?token=%s
                     
                     Caso você não tenha solicitado a recuperação, não se preocupe, apenas ignore este e-mail.
                     """, this.tokenService.generateRecoveryToken(storedUser.getId().toString()))
        );
    }
}
