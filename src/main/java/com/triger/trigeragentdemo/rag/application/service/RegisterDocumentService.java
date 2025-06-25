package com.triger.trigeragentdemo.rag.application.service;

import com.triger.trigeragentdemo.rag.adapter.in.web.RegisterTextDocumentController;
import com.triger.trigeragentdemo.rag.application.port.in.RegisterDocumentUseCase;
import com.triger.trigeragentdemo.rag.application.port.out.VectorStoreAddDocumentPort;
import com.triger.trigeragentdemo.rag.application.port.out.vector.AddDocumentVectorStoreCommend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Vector DB에 문서를 저장 하는 서비스
 */
@Service
@Slf4j
public class RegisterDocumentService implements RegisterDocumentUseCase {

    public RegisterDocumentService(VectorStoreAddDocumentPort vectorStoreAddDocumentPort) {
        this.vectorStoreAddDocumentPort = vectorStoreAddDocumentPort;
    }

    private final VectorStoreAddDocumentPort vectorStoreAddDocumentPort;

    /**
     * 문서 등록
     * @param registerDocumentCommend 사용자 입력 값 -> Out port commend
     */
    @Override
    public void resisterDocument(RegisterTextDocumentController.RequestRegisterTextDocument registerDocumentCommend) {
        vectorStoreAddDocumentPort.ingest(new AddDocumentVectorStoreCommend(
                registerDocumentCommend.content()
        ));
    }

}
