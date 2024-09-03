package com.flab.commerce.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonResponse<T> {

    private String statusCode; // 상태 코드
    private T data; // 출력 데이터

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>("200", data); // 요청 성공의 경우 상태 코드 200 반환
    }

    public static CommonResponse<Void> error(String errorCode) {
        return new CommonResponse<>(errorCode, null);

    }

}
