package com.flab.commerce.global.config;

import com.flab.commerce.domain.user.service.UserLoginService;
import com.flab.commerce.global.common.annotation.CheckUserIdArgumentResolver;
import com.flab.commerce.global.interceptor.AuthenticationInterceptor;
import com.flab.commerce.global.interceptor.LogInterceptor;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final CheckUserIdArgumentResolver checkUserIdArgumentResolver;
    private final UserLoginService loginService;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(checkUserIdArgumentResolver);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
            .order(1)
            .addPathPatterns("/**");

        registry.addInterceptor(new AuthenticationInterceptor(loginService))
            .order(2)
            .addPathPatterns("/**");


    }
}
