package com.staxter.demo.controller;

import com.staxter.demo.exception.WrongUserCredentialsException;
import com.staxter.demo.model.LoginResponse;
import com.staxter.demo.model.UserLogin;
import com.staxter.demo.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.staxter.demo.model.ErrorResponse.userWrongCreds;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/userservice")
public class UserLoginController {
    @Resource
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserLogin user) {

        try {
            LoginResponse response = authenticationService.loginUser(user);
            return ok().body(response);
        } catch (WrongUserCredentialsException e) {
            return new ResponseEntity<>(userWrongCreds(), UNAUTHORIZED);
        }
    }
}
