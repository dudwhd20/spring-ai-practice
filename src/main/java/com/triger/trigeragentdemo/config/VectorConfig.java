package com.triger.trigeragentdemo.config;

import org.springframework.ai.chroma.vectorstore.ChromaApi;
import org.springframework.ai.chroma.vectorstore.ChromaVectorStore;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.transformers.TransformersEmbeddingModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Spring boot Auto Configuration 부분 Chroma Vector 설정이
 * Spring boot AI 1.0 버전에서는 아직 배포가 안되어 우회 처리
 */
@Configuration
public class VectorConfig {

    @Value("${spring.ai.vectorstore.chroma.tenant-name}")
    private String tenantName;
    @Value("${spring.ai.vectorstore.chroma.database-name}")
    private String databaseName;
    @Value("${spring.ai.vectorstore.chroma.collection-name}")
    private String collectionName;

    private final TransformersEmbeddingModel transformersEmbeddingModel;

    public VectorConfig(TransformersEmbeddingModel transformersEmbeddingModel) {
        this.transformersEmbeddingModel = transformersEmbeddingModel;
    }

    @Bean
    public ChromaVectorStore chromaVectorStore(ChromaApi chromaApi) {
        return ChromaVectorStore.builder(chromaApi,transformersEmbeddingModel)
                .databaseName(databaseName)
                .tenantName(tenantName)
                .collectionName(collectionName)
                .initializeSchema(true)
                .build();
    }
}

