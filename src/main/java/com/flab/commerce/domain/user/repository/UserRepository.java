package com.flab.commerce.domain.user.repository;

import com.flab.commerce.domain.user.domain.User;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepository {

    private static Map<Long, User> users = new HashMap<>();
    private static Long userId = 0L;

    private static final UserRepository instance = new UserRepository();

    public static UserRepository getInstance() {
        return instance;
    }

    public UserRepository() {
    }

    public void insertUser(User user) {
        users.put(++userId, user);
    }

    public User getUser(long userId) {
        return users.get(userId);
    }

    public boolean existsByEmail(String email) {
        return users.containsKey(email);
    }

//    void insertUser(User user);
//
//    User findByEmail(String email);
//    boolean existsByEmail(String email);
}
