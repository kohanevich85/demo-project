package com.staxter.demo.service;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class PasswordHashService {

    public String encode(String painTextPassword) {
        return Base64.getEncoder().encodeToString(painTextPassword.getBytes());
    }

    public String decode(String encodedPassword) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
        return new String(decodedBytes);
    }
}
