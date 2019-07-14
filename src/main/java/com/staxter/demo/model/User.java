package com.staxter.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    @JsonIgnore
    private String plainTextPassword;
    @JsonIgnore
    private String hashedPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlainTextPassword() {
        return plainTextPassword;
    }

    public void setPlainTextPassword(String plainTextPassword) {
        this.plainTextPassword = plainTextPassword;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public static User create(UserRegistration userRegistration) {
        User user = new User();
        user.setFirstName(userRegistration.getFirstName());
        user.setLastName(userRegistration.getLastName());
        user.setUserName(userRegistration.getUserName());
        return user;
    }
}
