package com.anhanguera.crescerbem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenJwtInvalidException extends RuntimeException {
    public TokenJwtInvalidException(String message) {
        super(message);
    }
}
