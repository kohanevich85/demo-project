package com.staxter.demo.userrepository;

import com.staxter.demo.exception.UserAlreadyExistsException;
import com.staxter.demo.model.User;

import java.util.Optional;

public interface UserRepository {

    User createUser(User user) throws UserAlreadyExistsException;

    Optional<User> findByUserName(String userName);
}