package com.triger.trigeragentdemo.llm.adapter.out.llm;

import com.triger.trigeragentdemo.llm.application.port.out.ChatLLMPort;
import com.triger.trigeragentdemo.rag.application.port.in.QueryDocumentUseCase;
import com.triger.trigeragentdemo.rag.application.port.out.vector.QueryDocumentVectorStoreCommend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

/**
 * Ollama Adapter
 */
@Slf4j
@Component
public class OllamaAdapter implements ChatLLMPort {

    public OllamaAdapter(QueryDocumentUseCase queryDocumentUseCase, ChatClient.Builder chatClientBuilder) {
        this.queryDocumentUseCase = queryDocumentUseCase;
        this.chatClient = chatClientBuilder.build();
    }

    private final QueryDocumentUseCase queryDocumentUseCase;

    private final ChatClient chatClient;


    @Override
    public String chat(String userQuestion) {
        return chatClient.prompt()
                .user(userQuestion)
                .call()
                .content();
    }

    @Override
    public String ragChat(String userQuestion) {
        var documents = queryDocumentUseCase.queryDocument(new QueryDocumentVectorStoreCommend(userQuestion));
        var documentContent = String.join("", documents);
        return "";
    }

    @Override
    public String ragWithToolChain(String userQuestion) {
        return "";
    }
}
