package com.flab.commerce.global.interceptor;

import com.flab.commerce.domain.user.exception.UnauthenticatedUserException;
import com.flab.commerce.domain.user.service.UserLoginService;
import com.flab.commerce.global.common.annotation.LoginCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 인증의 경우 컨트롤러 호출 전에만 호출되면 되기에 preHandle 만 구현
 */
@Slf4j
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final UserLoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            LoginCheck loginCheck = hm.getMethodAnnotation(LoginCheck.class);

            if (loginCheck == null) {
                return true;
            }
            if (loginService.getCurrentUserId() == null) {
                throw new UnauthenticatedUserException("로그인이 필요한 서비스 입니다.");
            }
        }
        return true;
    }
}
