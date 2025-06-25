package com.triger.trigeragentdemo.rag.application.port.out;


import com.triger.trigeragentdemo.rag.application.port.out.vector.AddDocumentVectorStoreCommend;

/**
 * Vector DB에 문서를 추가하는 Out Port
 */
public interface VectorStoreAddDocumentPort {
    void ingest(AddDocumentVectorStoreCommend addCommend);
}
