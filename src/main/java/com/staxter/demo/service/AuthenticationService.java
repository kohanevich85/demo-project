package com.staxter.demo.service;

import com.staxter.demo.exception.WrongUserCredentialsException;
import com.staxter.demo.model.LoginResponse;
import com.staxter.demo.model.User;
import com.staxter.demo.model.UserLogin;
import com.staxter.demo.userrepository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthenticationService {


    @Resource
    private PasswordHashService passwordHashService;

    @Resource
    private UserRepository userRepository;

    public LoginResponse loginUser(UserLogin user) {
        User existingUser = userRepository.findByUserName(user.getUserName())
                .orElseThrow(() -> new WrongUserCredentialsException("User doesn't exist with user name: " + user.getUserName()));

        if (!isValidPassword(user, existingUser)) {
            throw new WrongUserCredentialsException("User doesn't exist with user name: " + user.getUserName());
        }
        return new LoginResponse();
    }

    private boolean isValidPassword(UserLogin user, User existingUser) {
        String passwordExistingUser = passwordHashService.decode(existingUser.getHashedPassword());
        return user.getPassword().equals(passwordExistingUser);
    }
}
