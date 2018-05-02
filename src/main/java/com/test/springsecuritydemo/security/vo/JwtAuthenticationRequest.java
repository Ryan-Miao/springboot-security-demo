package com.test.springsecuritydemo.security.vo;

import java.io.Serializable;
import lombok.Value;

@Value
public class JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String username;
    private String password;
}