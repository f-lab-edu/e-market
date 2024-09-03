package com.flab.commerce.global.common.annotation;

import static com.flab.commerce.global.constant.SessionConst.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CheckUserIdArgumentResolver implements
    HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        boolean hasAnnotation = parameter.hasParameterAnnotation(CheckUserId.class);
        boolean typeMatch = Long.class.isAssignableFrom(parameter.getParameterType());
        return hasAnnotation && typeMatch;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest(
            HttpServletRequest.class);

        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }

        return session.getAttribute(LOGIN_USER);
    }
}
