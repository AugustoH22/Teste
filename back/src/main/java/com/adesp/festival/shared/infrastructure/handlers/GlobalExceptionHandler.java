package com.adesp.festival.shared.infrastructure.handlers;

import com.adesp.festival.authentication.domain.exceptions.BadCredentialsException;
import com.adesp.festival.authentication.domain.exceptions.InactiveUserException;
import com.adesp.festival.shared.domain.entities.ApiErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException runtimeException){
        ApiErrorMessage errorMessage = new ApiErrorMessage(HttpStatus.BAD_REQUEST, LocalDateTime.now(), runtimeException.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getHttpStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException exception){
        ApiErrorMessage errorMessage = new ApiErrorMessage(HttpStatus.UNAUTHORIZED, LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getHttpStatus());
    }

    @ExceptionHandler(InactiveUserException.class)
    public ResponseEntity<?> handleInactiveUserException(InactiveUserException inactiveUserException){
        ApiErrorMessage errorMessage = new ApiErrorMessage(HttpStatus.UNAUTHORIZED, LocalDateTime.now(), inactiveUserException.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), errorMessage.getHttpStatus());
    }
}
