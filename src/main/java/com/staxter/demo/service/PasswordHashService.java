package com.staxter.demo.service;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

import static java.lang.Character.MIN_VALUE;

@Service
public class PasswordHashService {

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final SecureRandom RAND = new SecureRandom();
    private final String salt;

    public PasswordHashService() {
        byte[] salt = new byte[KEY_LENGTH];
        RAND.nextBytes(salt);
        this.salt =  Base64.getEncoder().encodeToString(salt);
    }

    public boolean verifyPassword (String password, String hashedPassword) {
        return hashedPassword.equals(hashPassword(password));
    }

    public String hashPassword(String password) {

        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);
        Arrays.fill(chars, MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(securePassword);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException("Exception encountered in hashPassword()");
        } finally {
            spec.clearPassword();
        }
    }
}
