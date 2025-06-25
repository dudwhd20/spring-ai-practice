package com.triger.trigeragentdemo.common;

import lombok.Getter;
import lombok.Setter;


/**
 * 공통 Response DTO 객체
 * @param <T>
 */
@Getter
@Setter
public class ApiResponse<T> {

    private boolean success;
    private T data;
    private String message;
    private Integer code;

    public ApiResponse(boolean success, T data, String message, Integer code) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null, 200);
    }

    public static ApiResponse<?> error(String message, int code) {
        return new ApiResponse<>(false, null, message, code);
    }

}
