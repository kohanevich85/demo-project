package com.staxter.demo.exception;

public class WrongUserCredentialsException extends RuntimeException {

    public WrongUserCredentialsException(String message) {
        super(message);
    }
}