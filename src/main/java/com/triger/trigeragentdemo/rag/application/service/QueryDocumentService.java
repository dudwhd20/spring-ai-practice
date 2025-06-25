package com.triger.trigeragentdemo.rag.application.service;

import com.triger.trigeragentdemo.common.ErrorCode;
import com.triger.trigeragentdemo.config.BusinessException;
import com.triger.trigeragentdemo.rag.application.port.in.QueryDocumentUseCase;
import com.triger.trigeragentdemo.rag.application.port.out.VectorStoreQueryDocumentPort;
import com.triger.trigeragentdemo.rag.application.port.out.vector.QueryDocumentVectorStoreCommend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class QueryDocumentService implements QueryDocumentUseCase {

    private final VectorStoreQueryDocumentPort vectorStoreQueryDocumentPort;

    public QueryDocumentService(VectorStoreQueryDocumentPort vectorStoreQueryDocumentPort) {
        this.vectorStoreQueryDocumentPort = vectorStoreQueryDocumentPort;
    }

    @Override
    public List<String> queryDocument(QueryDocumentVectorStoreCommend commend) {

        if (commend.content() == null || commend.content().isEmpty())
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE , "검색 값을 입력 해 주세요");

        int topK = 5;

        return vectorStoreQueryDocumentPort.query(commend.content(), topK);
    }


}
