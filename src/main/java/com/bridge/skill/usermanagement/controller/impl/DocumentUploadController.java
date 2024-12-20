package com.bridge.skill.usermanagement.controller.impl;

import com.bridge.skill.usermanagement.controller.IDocumentUploadController;
import com.bridge.skill.usermanagement.service.impl.DocumentUploadWorkFlow;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * The {@code DocumentUploadController} is used to upload document.
 *
 * @author surajyadav
 */
@RestController
@AllArgsConstructor
public class DocumentUploadController implements IDocumentUploadController {

    private final DocumentUploadWorkFlow defaultDocumentUpload;

    @Override
    public ResponseEntity<String> uploadDocument(final MultipartFile file, String userId, String documentType) throws FileSizeLimitExceededException {
        // TODO document type validation missing
        this.defaultDocumentUpload.uploadDocument(file, userId, documentType);
        return ResponseEntity.ok("Document uploaded successfully");
    }

}
