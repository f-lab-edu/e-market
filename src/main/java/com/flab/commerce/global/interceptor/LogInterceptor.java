package com.flab.commerce.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
public class LogInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        String uuid = UUID.randomUUID().toString();
        request.setAttribute("start", System.currentTimeMillis());
        request.setAttribute("request-id", uuid);
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler; // 호출 핸들러의 정보가 담겨있다.
        }
        log.info("REQ [{}] - calling [{}]", uuid, request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        log.info("postHandle [{}] - response in {}ms",
            request.getAttribute("request-id"),
            System.currentTimeMillis() - (long) request.getAttribute("start"));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception ex) throws Exception {
        log.info("RES [{}] - completed in {}ms",
            request.getAttribute("request-id"),
            System.currentTimeMillis() - (long) request.getAttribute("start"));
        if (ex != null) {
            log.error("Exception [{}] - exception", request.getAttribute("request-id"), ex);
        }
    }
}
