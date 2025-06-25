package com.triger.trigeragentdemo.rag.adapter.in.web;


import com.triger.trigeragentdemo.common.ApiResponse;
import com.triger.trigeragentdemo.rag.application.port.in.RegisterDocumentUseCase;
import com.triger.trigeragentdemo.rag.application.service.RegisterDocumentService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RAG 에 등록 하는 컨트롤러
 */
@RestController
@RequestMapping("/api/v1/rag")
@Slf4j
public class RegisterTextDocumentController {

    private final RegisterDocumentUseCase registerDocumentUseCase;

    public RegisterTextDocumentController(RegisterDocumentUseCase registerDocumentUseCase) {
        this.registerDocumentUseCase = registerDocumentUseCase;
    }

    /**
     * Text 만 등록 하는 부분 메서드
     * @param requestTextDocument 텍스트만 받는 DTO
     * @return 등록이 완료 되면 등록 되었다고 return
     */
    @PostMapping(value = "/text", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<String> registerTextDocument(@RequestBody RequestRegisterTextDocument requestTextDocument){
        registerDocumentUseCase.resisterDocument(requestTextDocument);
        return ApiResponse.ok("등록 완료");
    }


    /**
     * 임시로 만든 텍스트 등록 DTO 레코드
     * @param content
     */
    public record RequestRegisterTextDocument(
            @NotNull(message = "메시지 내용을 입력 해 주세요")
            @NotEmpty(message = "메시지 내용을 입력 해 주세요")
            String content
    ){
    }

}
