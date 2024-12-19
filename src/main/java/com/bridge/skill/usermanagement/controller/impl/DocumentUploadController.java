package com.bridge.skill.usermanagement.controller.impl;

import com.bridge.skill.usermanagement.controller.IDocumentUploadController;
import com.bridge.skill.usermanagement.service.intf.IUploadService;
import lombok.AllArgsConstructor;
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

    private final IUploadService iUploadService;

    @Override
    public String uploadDocument(final MultipartFile file) {

        /**
         * TODO
         *   Different validations needs to be handled for the input file.
         *   e.g. file size , null checks , format
         */
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File cannot empty!");
        }
        return this.iUploadService.uploadDocument(file, "");
    }

}
