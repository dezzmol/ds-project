package com.digitalspirit.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {
    public BadRequestException(HttpStatus status) {
        super(status);
    }

    public BadRequestException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public BadRequestException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
