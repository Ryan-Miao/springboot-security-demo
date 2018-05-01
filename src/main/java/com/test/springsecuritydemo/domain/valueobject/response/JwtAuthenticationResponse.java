package com.test.springsecuritydemo.domain.valueobject.response;

import java.io.Serializable;
import lombok.Value;

@Value
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;
}
