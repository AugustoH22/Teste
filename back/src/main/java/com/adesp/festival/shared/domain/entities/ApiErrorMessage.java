package com.adesp.festival.shared.domain.entities;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class ApiErrorMessage {
    private HttpStatus httpStatus;
    private LocalDateTime exceptionDateTime;
    private List<String> errors;

    public ApiErrorMessage(HttpStatus httpStatus, LocalDateTime exceptionDateTime, List<String> errors) {
        this.httpStatus = httpStatus;
        this.errors = errors;
        this.exceptionDateTime = exceptionDateTime;
    }

    public ApiErrorMessage(HttpStatus httpStatus, LocalDateTime exceptionDateTime, String errors) {
        this.httpStatus = httpStatus;
        this.errors = Collections.singletonList(errors);
        this.exceptionDateTime = exceptionDateTime;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public LocalDateTime getExceptionDateTime() {
        return exceptionDateTime;
    }

    public void setExceptionDateTime(LocalDateTime exceptionDateTime) {
        this.exceptionDateTime = exceptionDateTime;
    }
}
