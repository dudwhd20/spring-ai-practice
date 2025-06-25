package com.triger.trigeragentdemo.config;

import com.triger.trigeragentdemo.common.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Rest Controller Advice 필요 하면 사용자 어노테이션을 하나 만들어서
 * 그 어노테이션이 포함 된 곳은 무시하도록  supports 메서드에서 추가 가능
 * Rest Controller 에서 리턴 값을 간단히 입력 하면
 * ResponseEntity<ApiResponse<T>> 클래스에 자동으로 감싸져서 리턴
 */
@RestControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return !returnType.getParameterType().equals(ApiResponse.class) ||
                !returnType.getParameterType().equals(ResponseEntity.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // 이미 ApiResponse 인 경우 중복 감싸지 않음
        if (body instanceof ApiResponse<?>) {
            return body;
        }
        return ApiResponse.ok(body);
    }
}
