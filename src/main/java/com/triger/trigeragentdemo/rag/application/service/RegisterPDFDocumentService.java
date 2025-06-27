package com.triger.trigeragentdemo.rag.application.service;

import com.triger.trigeragentdemo.rag.adapter.in.web.RegisterTextDocumentController;
import com.triger.trigeragentdemo.rag.application.port.in.RegisterDocumentUseCase;
import com.triger.trigeragentdemo.rag.application.port.in.RegisterPDFDocumentUseCase;
import com.triger.trigeragentdemo.rag.application.port.out.VectorStoreADDPDFDocumentPort;
import com.triger.trigeragentdemo.rag.application.port.out.VectorStoreAddDocumentPort;
import com.triger.trigeragentdemo.rag.application.port.out.vector.AddDocumentVectorStoreCommend;
import com.triger.trigeragentdemo.rag.application.port.out.vector.AddPDFDocumentVectorStoreCommend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Vector DB에 문서를 저장 하는 서비스
 */
@Service
@Slf4j
public class RegisterPDFDocumentService implements RegisterPDFDocumentUseCase {

    private final VectorStoreADDPDFDocumentPort port;

    public RegisterPDFDocumentService(VectorStoreADDPDFDocumentPort port) {
        this.port = port;
    }


    @Override
    public void registerPDFDocument(File file) {
        port.ingest(new AddPDFDocumentVectorStoreCommend(file));
    }
}
