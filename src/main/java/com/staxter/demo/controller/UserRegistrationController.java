package com.staxter.demo.controller;

import com.staxter.demo.exception.UserAlreadyExistsException;
import com.staxter.demo.model.User;
import com.staxter.demo.model.UserRegistration;
import com.staxter.demo.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.staxter.demo.model.ErrorResponse.userAlreadyExists;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.ResponseEntity.ok;

@RestController()
@RequestMapping("/userservice")
public class UserRegistrationController {

    @Resource
    private RegistrationService userService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UserRegistration user) {
        try {
            User createdUser = userService.registerUser(user);
            return ok().body(createdUser);
        }catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(userAlreadyExists(), CONFLICT);
        }
    }
}
