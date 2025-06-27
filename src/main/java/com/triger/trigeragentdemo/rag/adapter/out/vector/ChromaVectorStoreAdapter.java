package com.triger.trigeragentdemo.rag.adapter.out.vector;

import com.triger.trigeragentdemo.common.ErrorCode;
import com.triger.trigeragentdemo.config.BusinessException;
import com.triger.trigeragentdemo.rag.application.port.out.VectorStoreADDPDFDocumentPort;
import com.triger.trigeragentdemo.rag.application.port.out.VectorStoreAddDocumentPort;
import com.triger.trigeragentdemo.rag.application.port.out.VectorStoreQueryDocumentPort;
import com.triger.trigeragentdemo.rag.application.port.out.vector.AddDocumentVectorStoreCommend;
import com.triger.trigeragentdemo.rag.application.port.out.vector.AddPDFDocumentVectorStoreCommend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chroma.vectorstore.ChromaVectorStore;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Vector Store 중 Chroma DB 의 port 부분을 구현한 구현체
 * Chroma 부분을 빼고 다른 클래스를 만들어서 구현하면 다른 Vector DB로 바로 바뀜
 * Out port 내용을 구현한 Adapter
 */
@Component
@Slf4j
public class ChromaVectorStoreAdapter implements VectorStoreAddDocumentPort, VectorStoreQueryDocumentPort , VectorStoreADDPDFDocumentPort {

    private final VectorStore vectorStore;
    private final TextSplitter textSplitter;

    public ChromaVectorStoreAdapter(ChromaVectorStore vectorStore) {
        this.vectorStore = vectorStore;
        this.textSplitter = new TokenTextSplitter(200, 100, 10, 10000, true);
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

//        if (commend.content().length() < 20) {
//            log.debug("20자리 미만의 컨텐츠");
//            chunks = List.of(new Document(commend.content()));
//        } else{
            log.debug("텍스트 스플리터 사용");
            chunks = textSplitter.split(new Document(commend.content()));
            log.debug("chunks Size : {}" , chunks.size());
//        }

        vectorStore.add(chunks.stream().map(e-> new Document("passage: " +  e.getText())).toList());
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
                .query(searchData).topK(topK)
                .similarityThreshold(0.8)
                .build());

        if (result != null && !result.isEmpty()) {
            log.debug("검색 된 문서 내용 🕵️‍♂️ : {}", result);
            return result.stream().map(Document::getText).toList();
        }

        throw new BusinessException(ErrorCode.ENTITY_NOT_FOUND, "검색 된 문서가 없습니다.");
    }

    @Override
    public void ingest(AddPDFDocumentVectorStoreCommend commend) {

        if(commend.file() == null || !commend.file().getName().toLowerCase().endsWith(".pdf")){
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE, "PDF 파일이 필요 합니다.");
        }

        var pdfDocs = readPdf(commend.file());
//        var docMetaData = Map.of("fileName", commend.file().getName(),
//                                                                                        "uploadTime", System.currentTimeMillis());
        var chunks = textSplitter.split(pdfDocs);

        vectorStore.add(chunks.stream().map(e-> new Document("passage: " +  e.getText())).toList());
    }


    /**
     * PDF Reader
     * @param file PDF 파일
     * @return List<Document>
     */
    private List<Document> readPdf(File file){
        Resource resource = new FileSystemResource(file);
        TikaDocumentReader reader = new TikaDocumentReader(resource);
        return reader.read();
    }
}
