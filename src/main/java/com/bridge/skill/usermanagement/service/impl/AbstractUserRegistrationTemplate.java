package com.bridge.skill.usermanagement.service.impl;

import com.bridge.skill.usermanagement.constants.enums.DocumentType;
import com.bridge.skill.usermanagement.dto.request.UserRequest;
import com.bridge.skill.usermanagement.dto.response.UserResponse;
import com.bridge.skill.usermanagement.service.intf.IUploadService;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *     This class provides a template for user registration defined with some steps
 *     that are common to all user registration flows.
 * </p>
 * <p>
 *     It is based on <code>Template Design Pattern</code>
 * </p>
 * @author surajyadav
 */
public abstract  class AbstractUserRegistrationTemplate extends AbstractUserDocumentUploadTemplate {

    protected AbstractUserRegistrationTemplate(final IUploadService uploadService) {
        super(uploadService);
    }

    /**
     * Method is used to read file content and convert it to byte array
     * @param file Document to be read
     * @return byte array
     */
    protected abstract byte[] readFileContent(final MultipartFile file);

    /**
     *
     * @param userRequest User request
     * @param bytes byte array
     * @return user response
     */
    protected abstract UserResponse createUser(final UserRequest userRequest, final byte[] bytes);

    /**
     * Invoking this method starts the execution of the user registration flow.
     * <p>
     * This method performs the following sequential steps:
     * </p>
     * <ol>
     *     <li>Validation of the file</li>
     *     <li>Reading the file content</li>
     *     <li>Creation of the user</li>
     *     <li>Generating the document name</li>
     *     <li>Uploading the document</li>
     * </ol>
     *
     * @param userRequest the request object containing user details
     * @param file        the file to be validated, read, and uploaded
     * @return the response entity containing the created user's details or an error status
     */
    public final UserResponse registerUser(final UserRequest userRequest, final MultipartFile file) throws FileSizeLimitExceededException {

        validateFile(file);
        final UserResponse user = createUser(userRequest, readFileContent(file));
        uploadFile(file, generateDocumentName(user.getId(), DocumentType.PROFILE_PIC.name()));
        return user;
    }
}

