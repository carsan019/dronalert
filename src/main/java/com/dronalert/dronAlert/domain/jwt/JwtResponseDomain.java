package com.dronalert.dronAlert.domain.jwt;

import java.io.Serial;
import java.io.Serializable;

public class JwtResponseDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
    public JwtResponseDomain(String jwtToken) {
        this.jwtToken = jwtToken;
    }
    public String getToken() {
        return this.jwtToken;
    }
}

