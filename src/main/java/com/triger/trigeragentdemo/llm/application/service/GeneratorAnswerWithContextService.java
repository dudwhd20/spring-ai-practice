package com.triger.trigeragentdemo.llm.application.service;

import com.triger.trigeragentdemo.llm.application.port.in.GeneratorAnswerWithContextUseCase;
import com.triger.trigeragentdemo.llm.application.port.out.ChatLLMPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GeneratorAnswerWithContextService implements GeneratorAnswerWithContextUseCase {

    public GeneratorAnswerWithContextService(ChatLLMPort chatLLMPort) {
        this.chatLLMPort = chatLLMPort;
    }

    private final ChatLLMPort chatLLMPort;

    @Override
    public String cheatWithLLM(String content) {
        return chatLLMPort.chat(content);
    }

}
