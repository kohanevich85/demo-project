package com.staxter.demo.controller;

import com.staxter.demo.AbstractTest;
import com.staxter.demo.model.ErrorResponse;
import com.staxter.demo.model.User;
import com.staxter.demo.model.UserRegistration;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import static com.staxter.demo.model.ErrorResponse.userAlreadyExists;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.OK;


public class UserRegistrationControllerTest extends AbstractTest {

    private static final String REGISTRATION_SERVICE = "/userservice/register";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String USER_NAME = "userName";
    private static final String PASSWORD = "Password";
    private static final int REGISTRATION_ID = 1;

    @Test
    public void testSuccessfulRegistration() {
        HttpEntity<UserRegistration> request = new HttpEntity<>(givenUser());
        ResponseEntity<User> response =
                getRestTemplate().exchange(REGISTRATION_SERVICE, POST, request, User.class);
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(expectedRegisteredUser());
    }

    @Test
    public void testUnsuccessfulRegistration() {
        givenUserAlreadyExists();
        HttpEntity<UserRegistration> request = new HttpEntity<>(givenUser());

        ResponseEntity<ErrorResponse> response =
                getRestTemplate().exchange(REGISTRATION_SERVICE, POST, request, ErrorResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(CONFLICT);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(userAlreadyExists());
    }

    private void givenUserAlreadyExists() {
        getRegistrationService().registerUser(givenUser());
    }

    private static UserRegistration givenUser() {
        UserRegistration user = new UserRegistration();
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setUserName(USER_NAME);
        user.setPassword(PASSWORD);
        return user;
    }

    private static User expectedRegisteredUser() {
        User user = new User();
        user.setId(REGISTRATION_ID);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setUserName(USER_NAME);
        return user;
    }
}