package com.flab.commerce.domain.user.service;

import static com.flab.commerce.global.constant.SessionConst.*;

import com.flab.commerce.domain.user.domain.User;
import com.flab.commerce.domain.user.dto.UserRequest.UserLoginRequest;
import com.flab.commerce.domain.user.dto.UserRequest.UserSignupRequest;
import com.flab.commerce.domain.user.repository.UserRepository;
import com.flab.commerce.domain.user.service.encryption.EncryptionService;
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

    private final UserRepository userRepository = UserRepository.getInstance();
    private final EncryptionService encoder;
    /**
     * 서블릿이 제공하는 기술 서블릿을 통해 session 생성시 JSESSIONID 이라는 이름의 쿠키를 생성 추정 불가한 랜덤값을 넣어준다.
     */
    private final HttpSession httpSession;


    public void signUp(UserSignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CommonException(ErrorCode.DUPLICATED_EMAIL);
        }

        User user = User.of(request, encoder.encryptPassword(request.getEmail(),
            request.getPassword()));
        userRepository.insertUser(user);
    }

    public CommonResponse<User> getUser(Long userId) {
        User user = userRepository.getUser(1);
        if (user == null) {
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }
        return CommonResponse.success(user);
    }

    public void login(UserLoginRequest request) {
        String encodedPassword = encoder.encryptPassword(request.getEmail(), request.getPassword());
        if (!userRepository.existsByEmailAndPassword(request.getEmail(), encodedPassword)) {
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }
        User user = userRepository.getUser(1);
        // 로그인 성공 로직
        httpSession.setAttribute(LOGIN_USER, user.getUserId());
        log.info("Logged in user: {}", httpSession.getAttribute(LOGIN_USER));
    }

    public void logout(Long userId) {
        try {
            httpSession.invalidate();
            log.info("Logged out user: {}", httpSession.getAttribute(LOGIN_USER));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
