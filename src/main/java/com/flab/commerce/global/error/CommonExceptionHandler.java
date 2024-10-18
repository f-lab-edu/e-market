package com.flab.commerce.global.error;

import com.flab.commerce.domain.user.exception.UnauthenticatedUserException;
import com.flab.commerce.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {


    @ExceptionHandler(CommonException.class)
    public ResponseEntity<?> applicationHandler(CommonException e) {
        log.error("error occurs {}", e.getMessage());
        return ResponseEntity.status(e.getErrorCode().getStatus())
            .body(CommonResponse.error(e.getErrorCode().toString()));
    }

    @ExceptionHandler(UnauthenticatedUserException.class)
    public ResponseEntity<CommonResponse> applicationHandler(UnauthenticatedUserException e) {
        log.error("user authentication error occurs  {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
            .body(CommonResponse.error(e.getMessage()));
    }
}
