package com.bridge.skill.usermanagement.service.impl;

import com.bridge.skill.usermanagement.exception.FileDeleteException;
import com.bridge.skill.usermanagement.exception.FileUploadException;
import com.bridge.skill.usermanagement.integration.cloudstorageclient.IStorageClient;
import com.bridge.skill.usermanagement.service.intf.IUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static com.bridge.skill.usermanagement.constants.UserConstants.FILES_DELETED_SUCCESSFULLY;

/**
 * Implementation is to handle upload documents / file based on the configured cloud storage of the system
 * @author surajyadav
 */
@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements IUploadService {

    private final IStorageClient iStorageClient;

    @Value("${documentBucketName}")
    private String documentBucketName;

    @Override
    public String uploadDocument(final MultipartFile file, String newFileName) {

        try {
            final InputStream inputStream = file.getInputStream();
            this.iStorageClient.uploadDocument(inputStream, newFileName, documentBucketName);
            return newFileName;
        } catch (IOException e) {
            throw new FileUploadException("File upload failed with name : " + file.getOriginalFilename() + " due to : " + e.getMessage());
        }
    }

    @Override
    public String deleteAllDocumentsForPrefix(String userId) {
        try {
            this.iStorageClient.deleteAllDocumentsForPrefix(userId, documentBucketName);
            return FILES_DELETED_SUCCESSFULLY;
        }
        catch (Exception e) {
            throw new FileDeleteException("File deletion failed with prefix : " + userId + " due to : " + e.getMessage());
        }
    }

}
