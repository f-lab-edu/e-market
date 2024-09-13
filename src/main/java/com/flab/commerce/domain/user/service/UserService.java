package com.flab.commerce.domain.user.service;

import static com.flab.commerce.global.constant.SessionConst.*;

import com.flab.commerce.domain.user.dao.UserRepository;
import com.flab.commerce.domain.user.domain.User;
import com.flab.commerce.domain.user.dto.UserRequest.UserLoginRequest;
import com.flab.commerce.domain.user.dto.UserRequest.UserSignupRequest;
import com.flab.commerce.domain.user.component.encryption.EncryptionComponent;
import com.flab.commerce.global.common.CommonResponse;
import com.flab.commerce.global.error.CommonException;
import com.flab.commerce.global.error.ErrorCode;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EncryptionComponent encoder;
    /**
     * 서블릿이 제공하는 기술 서블릿을 통해 session 생성시 JSESSIONID 이라는 이름의 쿠키를 생성 추정 불가한 랜덤값을 넣어준다.
     */
    private final HttpSession httpSession;


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
