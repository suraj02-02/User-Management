package com.bridge.skill.usermanagement.service.impl;

import com.bridge.skill.usermanagement.service.intf.IUploadService;
import com.bridge.skill.usermanagement.util.DocumentHelper;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *    This class provides a template for user document upload defined with some steps
 *    that are common to all user document upload flows.
 * </p>
 * @author surajyadav
 */
public abstract class AbstractUserDocumentUploadTemplate {

    private final IUploadService uploadService;

    protected AbstractUserDocumentUploadTemplate(final IUploadService uploadService) {
        this.uploadService = uploadService;
    }

    /**
     * Method is used to validate file.
     * If any kind of validation is required on the file it can be done here
     * @param file Document to be validated
     */
    protected abstract void validateFile(final MultipartFile file) throws FileSizeLimitExceededException;

    /**
     * Method is used to generate document name which is unique for a user and will be used to store the document
     * in S3
     * @param userId
     * @param documentType
     * @return document name
     */
    protected String generateDocumentName(final String userId, final String documentType) {
        return DocumentHelper.buildDocumentName(userId, documentType);
    }

    /**
     * Method is used to delegate the request to  <code>uploadService</code>
     * @param file file to be uploaded
     * @param fileName name of the file
     */
    protected void uploadFile(final MultipartFile file, final String fileName){
        uploadService.uploadDocument(file, fileName);
    }

    /**
     * Method is invoked to start the execution of the user document upload flow
     * @param file file to be uploaded
     * @param userId user id
     * @param documentType document type
     */
    public void uploadDocument(final MultipartFile file, final String userId, final String documentType) throws FileSizeLimitExceededException {
        validateFile(file);
        uploadFile(file, generateDocumentName(userId, documentType));
    }
}
