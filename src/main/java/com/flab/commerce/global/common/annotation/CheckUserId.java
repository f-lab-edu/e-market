package com.flab.commerce.global.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckUserId {
/**
 * 세션에 있는 로그인 정보를 조회하는 애노테이션
 * 사용 위치는 Parameter, 정보가 런타임까지 남아있음
 */
}
