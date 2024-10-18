package com.flab.commerce.domain.user.service;


import com.flab.commerce.domain.user.dao.UserRepository;
import com.flab.commerce.domain.user.domain.User;
import com.flab.commerce.domain.user.dto.UserRequest.UserSignupRequest;
import com.flab.commerce.domain.user.component.encryption.EncryptionComponent;
import com.flab.commerce.global.error.CommonException;
import com.flab.commerce.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EncryptionComponent encoder;

    @Transactional
    public void signUp(UserSignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CommonException(ErrorCode.DUPLICATED_EMAIL);
        }

        User user = User.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(encoder.encryptPassword(request.getEmail(), request.getPassword()))
            .phone(request.getPhone())
            .address(request.getAddress())
            .build();
        userRepository.save(user);
    }

}
