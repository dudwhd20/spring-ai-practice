package com.triger.trigeragentdemo.rag.application.port.out;

import com.triger.trigeragentdemo.rag.application.port.out.vector.AddPDFDocumentVectorStoreCommend;

public interface VectorStoreADDPDFDocumentPort {
    void ingest(AddPDFDocumentVectorStoreCommend commend);
}
