package com.staxter.demo.model;

import java.util.UUID;

/**
 *  Fake implementation
 */
public class LoginResponse {

    private String token;

    public LoginResponse() {
        this.token = UUID.randomUUID().toString();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
