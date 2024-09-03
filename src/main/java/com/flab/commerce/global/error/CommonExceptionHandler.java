package com.flab.commerce.global.error;

import com.flab.commerce.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
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
}
