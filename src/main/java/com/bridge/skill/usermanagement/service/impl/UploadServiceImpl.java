package com.bridge.skill.usermanagement.service.impl;

import com.bridge.skill.usermanagement.exception.FileUploadException;
import com.bridge.skill.usermanagement.integration.cloudstorageclient.IStorageClient;
import com.bridge.skill.usermanagement.service.intf.IUploadService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static com.bridge.skill.usermanagement.constants.UserConstants.FILE_UPLOADED_SUCCESSFULLY;

/**
 * Implementation is to handle upload documents / file based on the configured cloud storage of the system
 * @author surajyadav
 */
@Service
@AllArgsConstructor
public class UploadServiceImpl implements IUploadService {

    private final IStorageClient iStorageClient;

    @Override
    public String uploadDocument(final MultipartFile file) {

        final String originalFilename = file.getOriginalFilename();
        try {
            final InputStream inputStream = file.getInputStream();
            this.iStorageClient.uploadDocument(inputStream, originalFilename);
            return FILE_UPLOADED_SUCCESSFULLY + originalFilename;

        } catch (IOException e) {
            throw new FileUploadException("File upload failed with name : " + originalFilename + " due to : " + e.getMessage());
        }
    }

}
