package com.staxter.demo.controller;

import com.staxter.demo.AbstractTest;
import com.staxter.demo.model.LoginResponse;
import com.staxter.demo.model.UserLogin;
import com.staxter.demo.model.UserRegistration;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.OK;

public class UserLoginControllerTest extends AbstractTest {

    private static final String LOGIN_SERVICE = "/userservice/login";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String USER_NAME = "userName";
    private static final String PASSWORD = "Password";

    @Test
    public void testSuccessfulLogin() {
        givenExistingUser();
        HttpEntity<UserLogin> request = new HttpEntity<>(givenUser());

        ResponseEntity<LoginResponse> response =
                getRestTemplate().exchange(LOGIN_SERVICE, POST, request, LoginResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getToken()).isNotBlank();
    }

    @Test(expected = ResourceAccessException.class)
    public void testWrongLoginName() {
        HttpEntity<UserLogin> request = new HttpEntity<>(givenUser());
        getRestTemplate().exchange(LOGIN_SERVICE, POST, request, String.class);
    }

    @Test(expected = ResourceAccessException.class)
    public void testWrongLoginPassword() {
        HttpEntity<UserLogin> request = new HttpEntity<>(givenWrongUserPassword());
        getRestTemplate().exchange(LOGIN_SERVICE, POST, request, String.class);
    }

    private void givenExistingUser() {
        getRegistrationService().registerUser(prepareUserForRegistration());
    }

    private static UserRegistration prepareUserForRegistration() {
        UserRegistration user = new UserRegistration();
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setUserName(USER_NAME);
        user.setPassword(PASSWORD);
        return user;
    }

    private static UserLogin givenUser() {
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