package com.triger.trigeragentdemo.config;


import com.triger.trigeragentdemo.common.ErrorCode;
import lombok.Getter;

/**
 * 사용자 정의 예외 처리 Exception
 */
@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String detailMessage) {
        super(detailMessage);
        this.errorCode = errorCode;
    }
}
