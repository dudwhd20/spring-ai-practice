package com.triger.trigeragentdemo.config;


import com.triger.trigeragentdemo.common.ApiResponse;
import com.triger.trigeragentdemo.common.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

/**
 * 공통 에러 처리 클래스
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // ① 일반적인 IllegalArgumentException 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgument(IllegalArgumentException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // ② Bean Validation 실패 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidation(MethodArgumentNotValidException ex) {
        StringBuilder sb = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            sb.append(String.format("[%s]: %s ", error.getField(), error.getDefaultMessage()));
        });
        return buildErrorResponse(sb.toString(), HttpStatus.BAD_REQUEST);
    }

    // ③ 기타 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception e) {
        return new ResponseEntity<>(new ApiResponse<>(false, null,e.getMessage(),500), HttpStatus.BAD_REQUEST);
    }

    // ④ 비지니스 로직 예외 처리
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException ex) {

        var errorContent = Arrays.stream(ex.getStackTrace())
        .filter(e -> e.getClassName().startsWith("com.triger"))
        .toList();

        log.error("비지니스 로직 에러 : {}" , errorContent);

        ErrorCode errorCode = ex.getErrorCode();
        String message = ex.getMessage();
        return buildErrorResponse(message, errorCode.getStatus());
    }

    // ④ 공통 Response 생성
    private ResponseEntity<ApiResponse<?>> buildErrorResponse(String message, HttpStatus status) {
        ApiResponse<?> response = ApiResponse.error(message, status.value());
        return new ResponseEntity<>(response, status);
    }
}
