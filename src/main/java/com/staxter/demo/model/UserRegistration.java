package com.staxter.demo.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRegistration {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String userName;
    @NotNull
    private String password;
}
