package com.bridge.skill.usermanagement.controller.impl;

import com.bridge.skill.usermanagement.constants.enums.DocumentType;
import com.bridge.skill.usermanagement.controller.IUserController;
import com.bridge.skill.usermanagement.dto.request.UpdateUserRequest;
import com.bridge.skill.usermanagement.dto.request.UserRequest;
import com.bridge.skill.usermanagement.dto.response.UserProfileDetailResponse;
import com.bridge.skill.usermanagement.dto.response.UserResponse;
import com.bridge.skill.usermanagement.service.intf.IUploadService;
import com.bridge.skill.usermanagement.service.intf.IUserService;
import com.bridge.skill.usermanagement.util.DocumentHelper;
import com.bridge.skill.usermanagement.util.FileValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserController implements IUserController {

    private final IUserService userService;

    private final IUploadService uploadService;

    private final FileValidator fileValidator;

    @Override
    public ResponseEntity<UserResponse> registerUser(UserRequest userRequest, MultipartFile file) {
        try {
            if(file==null || fileValidator.isValidImage(file)) {
                UserResponse user = userService.createUser(userRequest);
                String newFileName = DocumentHelper.buildDocumentName(user.getId(), DocumentType.PROFILE_PIC.name());
                uploadService.uploadDocument(file, newFileName);
                return new ResponseEntity<>(user, HttpStatus.CREATED);
            }
            else {
                // ERROR MSG?
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<UserProfileDetailResponse> getUserProfileDetailsById(String userId) {
        return new ResponseEntity<>(userService.retrieveUserDetailsById(userId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> loginUser(String userName, String password) {
        boolean authenticated = userService.authenticateUser(userName, password);
        if (authenticated) {
            return ResponseEntity.ok("Login Successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not valid credentials");
    }

    @Override
    public ResponseEntity<String> deleteUserProfileDetailsById(String userId) {
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }

    @Override
    public ResponseEntity<String> updateUserProfileDetailsById(String userId, UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.updateUserProfileDetailsById(userId, updateUserRequest));
    }
}
