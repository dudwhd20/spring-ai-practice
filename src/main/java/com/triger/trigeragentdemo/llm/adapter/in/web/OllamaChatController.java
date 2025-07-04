package com.triger.trigeragentdemo.llm.adapter.in.web;


import com.triger.trigeragentdemo.common.ApiResponse;
import com.triger.trigeragentdemo.llm.application.port.in.GeneratorAnswerWithContextUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ollama Chat Controller
 */
@RestController
@RequestMapping("/api/v1/chat")
public class OllamaChatController {

    public OllamaChatController(GeneratorAnswerWithContextUseCase generatorAnswerWithContextUseCase) {
        this.generatorAnswerWithContextUseCase = generatorAnswerWithContextUseCase;
    }

    private final GeneratorAnswerWithContextUseCase generatorAnswerWithContextUseCase;

    /**
     * 기본 Chat Rag 제외
     * @param dto 유저 채팅
     * @return Ollama Response
     */
    @PostMapping
    public ApiResponse<String> chatOllama(@RequestBody RequestChatLLM dto){
        return ApiResponse.ok(generatorAnswerWithContextUseCase.cheatWithLLM(dto.userQuestion()));
    }

    /**
     * 기본 Chat Rag
     * @param dto 유저 채팅
     * @return Ollama Response
     */
    @PostMapping("/rag")
    public ApiResponse<String> ragChatOllama(@RequestBody RequestChatLLM dto){
        return ApiResponse.ok(generatorAnswerWithContextUseCase.retrieveCheatWithLLm(dto.userQuestion()));
    }
    /**
     * 기본 Chat Tool
     * @param dto 유저 채팅
     * @return Ollama Response
     */
    @PostMapping("/tool")
    public ApiResponse<String> toolWithChatOllama(@RequestBody RequestChatLLM dto){
        return ApiResponse.ok(generatorAnswerWithContextUseCase.toolChainWithLLM(dto.userQuestion()));
    }

}
