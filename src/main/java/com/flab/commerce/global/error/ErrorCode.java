package com.flab.commerce.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "40301", "email is duplicated"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "40401", "user not found"),
    CART_PRODUCT_IS_EMPTY(HttpStatus.BAD_REQUEST, "40001", "cart product is empty"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "50101", "Internal server error"),
    IMAGE_FILE_EXCEPTION(HttpStatus.BAD_REQUEST,"40002", "image file exception"),
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
