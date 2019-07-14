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

    public static User create(UserRegistration userRegistration) {
        User user = new User();
        user.setFirstName(userRegistration.getFirstName());
        user.setLastName(userRegistration.getLastName());
        user.setUserName(userRegistration.getUserName());
        return user;
    }
}
