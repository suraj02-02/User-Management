package com.bridge.skill.usermanagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/document")
public interface IDocumentUploadController {

    /**
     * Invoke this endpoint to upload document
     * @param file
     * @return message
     */
    @PostMapping("/upload")
    String uploadDocument(@RequestParam("file") final MultipartFile file);

}
