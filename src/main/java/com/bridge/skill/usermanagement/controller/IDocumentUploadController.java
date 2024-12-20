package com.bridge.skill.usermanagement.controller;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("user/{userId}/document/{documentType}")
public interface IDocumentUploadController {

    /**
     * Invoke this endpoint to upload document for a user
     * @param file
     * @param userId
     * @param documentType
     * @return
     */
    @PostMapping("/upload")
    ResponseEntity<String> uploadDocument(@RequestParam("file") final MultipartFile file,
                                         @PathVariable final String userId,
                                         @PathVariable final String documentType) throws FileSizeLimitExceededException;

}
