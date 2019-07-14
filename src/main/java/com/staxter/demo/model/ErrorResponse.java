package com.staxter.demo.model;

import lombok.Data;

@Data
public class ErrorResponse {
    private String code;
    private String message;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ErrorResponse userAlreadyExists() {
        return new ErrorResponse("USER_ALREADY_EXISTS", "A user with given username already exists");
    }

    public static ErrorResponse userWrongCreds() {
        return new ErrorResponse("USER_WRONG_CREDS", "Provided username or password is invalid");
    }
}
