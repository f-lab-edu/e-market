package com.flab.commerce.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "40301", "email is duplicated"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "40401", "user not found"),
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
