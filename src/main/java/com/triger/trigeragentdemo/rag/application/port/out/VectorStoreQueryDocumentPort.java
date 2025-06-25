package com.triger.trigeragentdemo.rag.application.port.out;

import java.util.List;

/**
 * Vector DB 에서 거리 찾을 텍스트 내용과
 * 검색 된 상위 몇개를 가져 올 지 정하는 Out Port
 */
public interface VectorStoreQueryDocumentPort {
    List<String> query(String searchData, int topK);
}
