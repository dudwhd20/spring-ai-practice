package com.triger.trigeragentdemo.llm.application.port.out;

public interface ChatLLMPort {

    String chat(String userQuestion);

    String ragChat(String userQuestion);

    String ragWithToolChain(String userQuestion);
}
