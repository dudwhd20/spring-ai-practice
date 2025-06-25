package com.triger.trigeragentdemo.rag.application.port.in;

import com.triger.trigeragentdemo.rag.application.port.out.vector.QueryDocumentVectorStoreCommend;

import java.util.List;

public interface QueryDocumentUseCase {
    List<String> queryDocument(QueryDocumentVectorStoreCommend commend);
}
