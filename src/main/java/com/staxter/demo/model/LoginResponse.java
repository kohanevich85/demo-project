package com.staxter.demo.model;

import lombok.Data;

import java.util.UUID;

/**
 *  Fake implementation
 */
@Data
public class LoginResponse {

    private String token;

    public LoginResponse() {
        this.token = UUID.randomUUID().toString();
    }
}
