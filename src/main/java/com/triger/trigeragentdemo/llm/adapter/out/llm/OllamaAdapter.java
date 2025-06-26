package com.triger.trigeragentdemo.llm.adapter.out.llm;

import com.triger.trigeragentdemo.llm.application.port.out.ChatLLMPort;
import com.triger.trigeragentdemo.rag.application.port.in.QueryDocumentUseCase;
import com.triger.trigeragentdemo.rag.application.port.out.vector.QueryDocumentVectorStoreCommend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.template.TemplateRenderer;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Ollama Adapter
 */
@Slf4j
@Component
public class OllamaAdapter implements ChatLLMPort {

    public OllamaAdapter(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder.build();
        this.vectorStore = vectorStore;
    }

    private final VectorStore vectorStore;
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

        var qaPromptTemplate = PromptTemplate.builder()
                .renderer(StTemplateRenderer.builder().startDelimiterToken('<').endDelimiterToken('>').build())
                .template("""
                <query>
                당신은 사내 지식 기반 AI입니다. 아래의 문서 내용을 참고 하여 답변을 친절하게 주세요.
                문서 내용에 값이 비어있는거나 참조 할 만한 문서가 없는 경우 검색이 안되었다고 답변합니다.
                문서내에 내용을 모르면 모른다고 답 해 주세요
                
                아래와 같은 JSON FROM 을 이용 하여 답 해 주세요
                    {
                        "answer" : "답변내용"
                    }
                    --문서 내용---
                    <question_answer_context>
                    ------------
                """).build();

//        QuestionAnswerAdvisor qaAdvisor = QuestionAnswerAdvisor.builder(vectorStore)
//            .promptTemplate(qaPromptTemplate.)
//            .build();
        var list = vectorStore.similaritySearch("query: " + userQuestion).stream().map(e->
                Objects.requireNonNull(e.getText()).replaceFirst("^passage:\\s*", "")
        ).collect(Collectors.joining("\n"));

        var r = chatClient
                .prompt(qaPromptTemplate.create(Map.of("question_answer_context", list, "query",userQuestion)))
                .call()
                .chatResponse()
                ;

        log.info("내용 전체 확인용 {}", r);


        return r.getResult().getOutput().getText()
                ;
    }

    @Override
    public String ragWithToolChain(String userQuestion) {
        return "";
    }
}
