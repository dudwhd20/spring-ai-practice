package com.triger.trigeragentdemo.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 공통 에러코드 내용
 * Enum 에 추가하여 사용 하면 됨
 */
@Getter
public enum ErrorCode {
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "Invalid Input Value"),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "Entity Not Found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
