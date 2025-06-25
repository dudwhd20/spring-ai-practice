package com.triger.trigeragentdemo.rag.application.port.out.vector;

/**
 * Vector Store 에 Text 문서를 저장 하는 Out port Commend
 * @param content Text Content
 */
public record AddDocumentVectorStoreCommend(
        String content
) {
}
