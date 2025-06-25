package com.triger.trigeragentdemo.rag.adapter.out.vector;

import com.triger.trigeragentdemo.common.ErrorCode;
import com.triger.trigeragentdemo.config.BusinessException;
import com.triger.trigeragentdemo.rag.application.port.out.VectorStoreAddDocumentPort;
import com.triger.trigeragentdemo.rag.application.port.out.VectorStoreQueryDocumentPort;
import com.triger.trigeragentdemo.rag.application.port.out.vector.AddDocumentVectorStoreCommend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chroma.vectorstore.ChromaVectorStore;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Vector Store 중 Chroma DB 의 port 부분을 구현한 구현체
 * Chroma 부분을 빼고 다른 클래스를 만들어서 구현하면 다른 Vector DB로 바로 바뀜
 * Out port 내용을 구현한 Adapter
 */
@Component
@Slf4j
public class ChromaVectorStoreAdapter implements VectorStoreAddDocumentPort, VectorStoreQueryDocumentPort {

    private final VectorStore vectorStore;
    private final TextSplitter textSplitter;

    public ChromaVectorStoreAdapter(ChromaVectorStore vectorStore) {
        this.vectorStore = vectorStore;
        this.textSplitter = new TokenTextSplitter(300, 200, 10, 10000, true);
    }

    /**
     * Chroma vector DB 저장
     *
     * @param commend 저장 할 객제 내용 / 추후 메타데이터 등의 정보가 commend 에 추가 될 수 도 있음
     */
    @Override
    public void ingest(AddDocumentVectorStoreCommend commend) {
        log.debug("Request Add Vector : {}", commend.content());

        if(commend.content() == null || commend.content().isEmpty()){
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE , "입력 값은 필수 입니다.");
        }

        List<Document> chunks;

        if (commend.content().length() < 20) {
            chunks = List.of(new Document(commend.content()));
        } else{
            chunks = textSplitter.split(new Document(commend.content()));
        }

        vectorStore.add(chunks);
    }

    /**
     * chroma vector DB 에서 내용 찾기
     *
     * @param searchData 검색 내용
     * @param topK       vector 상위 순위
     * @return 검색 된 문서들
     */
    @Override
    public List<String> query(String searchData, int topK) {
        List<Document> result = this.vectorStore.similaritySearch(SearchRequest.builder()
                .query(searchData).topK(5)
                .similarityThreshold(0.5)
                .build());

        if (result != null && !result.isEmpty()) {
            log.debug("검색 된 문서 내용 🕵️‍♂️ : {}", result);
            return result.stream().map(Document::getText).toList();
        }

        throw new BusinessException(ErrorCode.ENTITY_NOT_FOUND, "검색 된 문서가 없습니다.");
    }
}
