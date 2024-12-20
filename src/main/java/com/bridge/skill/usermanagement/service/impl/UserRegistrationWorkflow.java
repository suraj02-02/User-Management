package com.bridge.skill.usermanagement.service.impl;

import com.bridge.skill.usermanagement.dto.request.UserRequest;
import com.bridge.skill.usermanagement.dto.response.UserResponse;
import com.bridge.skill.usermanagement.service.intf.IUploadService;
import com.bridge.skill.usermanagement.service.intf.IUserService;
import com.bridge.skill.usermanagement.util.FileValidator;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserRegistrationWorkflow extends AbstractUserRegistrationTemplate {

    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    private final IUserService userService;
    private final FileValidator fileValidator;
    private final IUploadService uploadService;

    public UserRegistrationWorkflow(final IUserService userService , final FileValidator fileValidator , final IUploadService uploadService) {
        super(uploadService);
        this.userService = userService;
        this.fileValidator = fileValidator;
        this.uploadService = uploadService;
    }

    @Override
    protected void validateFile(final MultipartFile file) throws FileSizeLimitExceededException {
        if(file !=null && !file.isEmpty()) {
            fileValidator.validateImage(file);
        }
    }

    @Override
    protected byte[] readFileContent(final MultipartFile file) {
        if (file == null) {
            return EMPTY_BYTE_ARRAY;
        }
        try {
            return file.getInputStream().readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file content", e);
        }
    }

    @Override
    protected UserResponse createUser(final UserRequest userRequest, final byte[] bytes) {
        return userService.createUser(userRequest , bytes);
    }


    @Override
    protected void uploadFile(final MultipartFile file, final String fileName) {
        if (!(file== null || file.isEmpty())){
            uploadService.uploadDocument(file, fileName);
        }
    }
}
