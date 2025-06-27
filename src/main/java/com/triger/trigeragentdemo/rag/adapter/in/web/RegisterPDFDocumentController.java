package com.triger.trigeragentdemo.rag.adapter.in.web;

import com.triger.trigeragentdemo.common.ApiResponse;
import com.triger.trigeragentdemo.common.ErrorCode;
import com.triger.trigeragentdemo.config.BusinessException;
import com.triger.trigeragentdemo.rag.application.port.in.RegisterPDFDocumentUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/api/v1/rag")
@Slf4j
public class RegisterPDFDocumentController {

    private final RegisterPDFDocumentUseCase registerPDFDocumentUseCase;

    public RegisterPDFDocumentController(RegisterPDFDocumentUseCase registerPDFDocumentUseCase) {
        this.registerPDFDocumentUseCase = registerPDFDocumentUseCase;
    }


    @PostMapping(path = "/pdf")
    public ApiResponse<String> registerPDF(@RequestParam("file") MultipartFile file) throws IOException {

        if(file == null || !file.getOriginalFilename().toLowerCase().endsWith(".pdf")){
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE, "PDF 파일이 필요 합니다.");
        }
        var f = File.createTempFile("upload_" , ".pdf");
        file.transferTo(f);

        registerPDFDocumentUseCase.registerPDFDocument(f);

        return  ApiResponse.ok("등록되었습니다");
    }

}
