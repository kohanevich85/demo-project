package com.staxter.demo.userrepository;

import com.staxter.demo.exception.UserAlreadyExistsException;
import com.staxter.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
public class UserMemoryRepository implements UserRepository {

    private Map<String, User> users = new HashMap<>();

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        if (users.containsKey(user.getUserName())) {
            throw new UserAlreadyExistsException("User is already exists " + user.getUserName());
        }
        user.setId(getNextId());
        users.put(user.getUserName(), user);
        return user;
    }

    private int getNextId() {
        return users.size() + 1;
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return ofNullable(users.get(userName));
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}
