package com.triger.trigeragentdemo.rag.adapter.in.web;

import com.triger.trigeragentdemo.common.ApiResponse;
import com.triger.trigeragentdemo.rag.application.port.in.QueryDocumentUseCase;
import com.triger.trigeragentdemo.rag.application.port.out.vector.QueryDocumentVectorStoreCommend;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rag/query")
public class QueryDocumentController {


    public QueryDocumentController(QueryDocumentUseCase queryDocumentUseCase) {
        this.queryDocumentUseCase = queryDocumentUseCase;
    }

    private final QueryDocumentUseCase queryDocumentUseCase;


    @GetMapping(value = "/{content}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<List<String>> queryDocument(@PathVariable String content){
        return ApiResponse.ok(queryDocumentUseCase.queryDocument(new QueryDocumentVectorStoreCommend(content)));
    }
}
