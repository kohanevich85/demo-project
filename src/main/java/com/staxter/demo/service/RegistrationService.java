package com.staxter.demo.service;

import com.staxter.demo.model.User;
import com.staxter.demo.model.UserRegistration;
import com.staxter.demo.userrepository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.staxter.demo.model.User.create;

@Service
public class RegistrationService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private PasswordHashService passwordHashService;

    public User registerUser(UserRegistration userRegistration) {
        User user = create(userRegistration);
        String encodedPassword = passwordHashService.encode(userRegistration.getPassword());
        user.setHashedPassword(encodedPassword);

        return userRepository.createUser(user);
    }
}
