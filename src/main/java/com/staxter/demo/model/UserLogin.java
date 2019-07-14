package com.staxter.demo.model;

import javax.validation.constraints.NotNull;

public class UserLogin {

    @NotNull
    private String userName;
    @NotNull
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
