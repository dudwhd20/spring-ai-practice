package com.triger.trigeragentdemo.rag.application.port.out.vector;

/**
 * Vector DB 에서 문서를 검색하는 Out port Commend
 * @param content
 */
public record QueryDocumentVectorStoreCommend (
        String content
){

}
