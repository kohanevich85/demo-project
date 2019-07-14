package com.staxter.demo.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserLogin {

    @NotNull
    private String userName;
    @NotNull
    private String password;
}
