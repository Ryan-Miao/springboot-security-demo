package com.test.springsecuritydemo.security;

import java.io.Serializable;
import lombok.Value;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    @Value
    public static class JwtAuthenticationRequest implements Serializable {

        private static final long serialVersionUID = -8445943548965154778L;

        private String username;
        private String password;
    }
}
