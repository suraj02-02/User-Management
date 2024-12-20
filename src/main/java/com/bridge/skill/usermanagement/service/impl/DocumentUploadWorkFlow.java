package com.bridge.skill.usermanagement.service.impl;

import com.bridge.skill.usermanagement.service.intf.IUploadService;
import com.bridge.skill.usermanagement.util.FileValidator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentUploadWorkFlow extends AbstractUserDocumentUploadTemplate {

    private final FileValidator fileValidator;
    private final IUploadService uploadService;

    public DocumentUploadWorkFlow(final IUploadService uploadService , final FileValidator fileValidator) {
        super(uploadService);
        this.fileValidator = fileValidator;
        this.uploadService = uploadService;
    }

    @Override
    protected void validateFile(MultipartFile file) {
        if(file !=null && !file.isEmpty()) {
            fileValidator.validatePdf(file);
        }
    }

    @Override
    protected void uploadFile(MultipartFile file, String fileName) {
        uploadService.uploadDocument(file, fileName);
    }
}
