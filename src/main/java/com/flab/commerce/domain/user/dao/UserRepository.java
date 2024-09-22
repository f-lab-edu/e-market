package com.flab.commerce.domain.user.dao;

import com.flab.commerce.domain.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRepository {

    void save(User user);

    User findById(Long userId);

    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    User findByEmail(String email);
}
