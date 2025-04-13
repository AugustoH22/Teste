package com.adesp.festival.authentication.infrastructure.controllers;

import com.adesp.festival.authentication.application.dtos.request.*;
import com.adesp.festival.authentication.application.dtos.response.*;
import com.adesp.festival.authentication.application.usecases.*;
import com.adesp.festival.authentication.domain.entities.Invite;
import com.adesp.festival.authentication.domain.entities.User;
import com.adesp.festival.authentication.domain.enums.Roles;
import com.adesp.festival.authentication.infrastructure.mappers.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final SignInUseCase signInUseCase;
    private final InviteUserUseCase inviteUserUseCase;
    private final SignUpUseCase signUpUseCase;
    private final RequestPasswordRecoveryUseCase requestPasswordRecoveryUseCase;
    private final RecoveryPasswordUseCase recoveryPasswordUseCase;
    private final FindInviteByDestinationUseCase findInviteByDestinationUseCase;
    private final UserMapper userMapper;

    public AuthenticationController(SignInUseCase signInUseCase, InviteUserUseCase inviteUserUseCase, SignUpUseCase signUpUseCase, RequestPasswordRecoveryUseCase requestPasswordRecoveryUseCase, RecoveryPasswordUseCase recoveryPasswordUseCase, FindInviteByDestinationUseCase findInviteByDestinationUseCase, UserMapper userMapper) {
        this.signInUseCase = signInUseCase;
        this.inviteUserUseCase = inviteUserUseCase;
        this.signUpUseCase = signUpUseCase;
        this.requestPasswordRecoveryUseCase = requestPasswordRecoveryUseCase;
        this.recoveryPasswordUseCase = recoveryPasswordUseCase;
        this.findInviteByDestinationUseCase = findInviteByDestinationUseCase;
        this.userMapper = userMapper;
    }

    @PostMapping("/v1/auth/signin")
    public ResponseEntity<SignInResponse> login(@RequestBody SignInRequest signInRequest){
        HashMap<String, String> tokens = this.signInUseCase.execute(signInRequest.login(), signInRequest.password());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new SignInResponse(tokens.get("accessToken"), tokens.get("refreshToken")));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/v1/auth/invite")
    public ResponseEntity<InviteUserResponse> invite(@RequestBody InviteUserRequest inviteUserRequest, HttpServletRequest request){
        this.inviteUserUseCase.execute(Long.parseLong(request.getAttribute("subject").toString()), inviteUserRequest.email(), Roles.valueOf(inviteUserRequest.role()));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new InviteUserResponse("O convite foi enviado com sucesso!"));
    }

    @PostMapping("/v1/auth/request-recovery")
    public ResponseEntity<RequestRecoverySuccessResponse> requestRecovery(@RequestBody PasswordRecoveryRequest passwordRecoveryRequest){
        this.requestPasswordRecoveryUseCase.execute(passwordRecoveryRequest.email());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new RequestRecoverySuccessResponse("O E-mail para recuperação foi enviado com sucesso!"));
    }

    @PostMapping("/v1/auth/signup")
    public ResponseEntity<SignUpResponse> signup(@RequestBody SignUpRequest signUpRequest){
        User mappedUser = this.userMapper.signUpRequestToDomain(signUpRequest);
        User storedUser = this.signUpUseCase.execute(mappedUser, signUpRequest.inviteToken());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.userMapper.userToSignUpResponse(storedUser));
    }

    @PatchMapping("/v1/auth/recovery")
    public ResponseEntity<PasswordRecoveryResponse> recovery(@RequestParam String recoveryToken, @RequestBody RecoveryRequest recoveryRequest){
        this.recoveryPasswordUseCase.execute(recoveryToken, recoveryRequest.password());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new PasswordRecoveryResponse("Senha redefinida com sucesso. Faça login com suas novas credenciais!"));
    }

    @GetMapping("/v1/auth/signup/verify-invite")
    public ResponseEntity<?> findInviteByDestination(@RequestParam("inviteToken") String inviteToken){
        Invite invite = this.findInviteByDestinationUseCase.execute(inviteToken);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new InviteDestinationResponse(invite.getDestinationEmail()));
    }
}
