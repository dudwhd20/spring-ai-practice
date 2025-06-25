package com.triger.trigeragentdemo.rag.application.port.in;

import com.triger.trigeragentdemo.rag.adapter.in.web.RegisterTextDocumentController;

public interface RegisterDocumentUseCase {

    void resisterDocument(RegisterTextDocumentController.RequestRegisterTextDocument requestRegisterTextDocument);
}
