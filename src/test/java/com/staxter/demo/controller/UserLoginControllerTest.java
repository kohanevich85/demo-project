package com.staxter.demo.controller;

import com.staxter.demo.AbstractTest;
import com.staxter.demo.model.LoginResponse;
import com.staxter.demo.model.UserLogin;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.OK;

public class UserLoginControllerTest extends AbstractTest {

    private static final String LOGIN_SERVICE = "/userservice/login";
    private static final String PASSWORD = "Password";

    @Test
    public void testSuccessfulLogin() {
        givenExistingUser();
        HttpEntity<UserLogin> request = new HttpEntity<>(givenUserCreds());

        ResponseEntity<LoginResponse> response =
                getRestTemplate().exchange(LOGIN_SERVICE, POST, request, LoginResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getToken()).isNotBlank();
    }

    @Test(expected = ResourceAccessException.class)
    public void testWrongLoginName() {
        HttpEntity<UserLogin> request = new HttpEntity<>(givenUserCreds());
        getRestTemplate().exchange(LOGIN_SERVICE, POST, request, String.class);
    }

    @Test(expected = ResourceAccessException.class)
    public void testWrongLoginPassword() {
        HttpEntity<UserLogin> request = new HttpEntity<>(givenWrongUserPassword());
        getRestTemplate().exchange(LOGIN_SERVICE, POST, request, String.class);
    }

    private void givenExistingUser() {
        getRegistrationService().registerUser(givenUser());
    }

    private static UserLogin givenUserCreds() {
        UserLogin user = new UserLogin();
        user.setUserName(USER_NAME);
        user.setPassword(PASSWORD);
        return user;
    }

    private static UserLogin givenWrongUserPassword() {
        UserLogin user = new UserLogin();
        user.setUserName(USER_NAME);
        user.setPassword(PASSWORD);
        return user;
    }
}