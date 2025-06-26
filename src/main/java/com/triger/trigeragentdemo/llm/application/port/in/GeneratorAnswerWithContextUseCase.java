package com.triger.trigeragentdemo.llm.application.port.in;

public interface GeneratorAnswerWithContextUseCase {
    String cheatWithLLM(String content);

    String retrieveCheatWithLLm(String content);
}
