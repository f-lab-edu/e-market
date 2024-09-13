package com.flab.commerce.domain.user.service;

import static com.flab.commerce.global.constant.SessionConst.LOGIN_USER;

import com.flab.commerce.domain.user.component.encryption.EncryptionComponent;
import com.flab.commerce.domain.user.dao.UserRepository;
import com.flab.commerce.domain.user.domain.User;
import com.flab.commerce.domain.user.dto.UserRequest.UserLoginRequest;
import com.flab.commerce.global.error.CommonException;
import com.flab.commerce.global.error.ErrorCode;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserLoginService {

    private final UserRepository userRepository;
    private final EncryptionComponent encoder;
    /**
     * 서블릿이 제공하는 기술 서블릿을 통해 session 생성시 JSESSIONID 이라는 이름의 쿠키를 생성 추정 불가한 랜덤값을 넣어준다.
     */
    private final HttpSession httpSession;

    public void login(UserLoginRequest request) {
        String encodedPassword = encoder.encryptPassword(request.getEmail(), request.getPassword());
        if (!userRepository.existsByEmailAndPassword(request.getEmail(), encodedPassword)) {
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }
        User user = userRepository.findByEmail(request.getEmail());
        // 로그인 성공 로직
        httpSession.setAttribute(LOGIN_USER, user.getUserId());
        log.info("Logged in user: {}", httpSession.getAttribute(LOGIN_USER));
    }

    public void logout(Long userId) {
        try {
            log.info("Request Logged out user: {}", httpSession.getAttribute(LOGIN_USER));
            httpSession.invalidate();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            log.info("Logged out user: {}", httpSession.getAttribute(LOGIN_USER));
        }
    }

    public Long getCurrentUserId() {
        return (Long) httpSession.getAttribute(LOGIN_USER);
    }
}
