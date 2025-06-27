package com.triger.trigeragentdemo.rag.application.port.out.vector;

import java.io.File;
import java.util.Map;

/**
 * Vector Store 에 PDF 문서를 저장 하는 Out port Commend
 */
public record AddPDFDocumentVectorStoreCommend(
        File file
) {
}
