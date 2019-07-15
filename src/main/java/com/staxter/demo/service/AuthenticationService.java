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

    @Resource
    private JwtTokenService jwtTokenService;

    public LoginResponse loginUser(UserLogin user) {
        User existingUser = userRepository.findByUserName(user.getUserName())
                .orElseThrow(() -> new WrongUserCredentialsException("User doesn't exist with user name: " + user.getUserName()));

        if (!isValidPassword(user, existingUser)) {
            throw new WrongUserCredentialsException("User doesn't exist with user name: " + user.getUserName());
        }
        String token = jwtTokenService.generateToken(existingUser.getId());
        return new LoginResponse(token);
    }

    private boolean isValidPassword(UserLogin user, User existingUser) {
        return passwordHashService.verifyPassword(user.getPassword(), existingUser.getHashedPassword());
    }
}
