package com.bridge.skill.usermanagement.controller.impl;

import com.bridge.skill.usermanagement.controller.IUserController;
import com.bridge.skill.usermanagement.dto.request.UpdateUserRequest;
import com.bridge.skill.usermanagement.dto.request.UserRequest;
import com.bridge.skill.usermanagement.dto.response.UserProfileDetailResponse;
import com.bridge.skill.usermanagement.dto.response.UserResponse;
import com.bridge.skill.usermanagement.service.impl.UserRegistrationWorkflow;
import com.bridge.skill.usermanagement.service.intf.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UserController implements IUserController {

    private final IUserService userService;
    private final UserRegistrationWorkflow userRegistration;

    @Override
    public ResponseEntity<UserResponse> registerUser(final UserRequest userRequest, final MultipartFile file) throws FileSizeLimitExceededException {
        return new ResponseEntity<>(userRegistration.registerUser(userRequest, file) , HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserProfileDetailResponse> getUserProfileDetailsById(final String userId) {
        return new ResponseEntity<>(userService.retrieveUserDetailsById(userId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> loginUser(final String userName, final String password) {
        boolean authenticated = userService.authenticateUser(userName, password);
        if (authenticated) {
            return ResponseEntity.ok("Login Successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not valid credentials");
    }

    @Override
    public ResponseEntity<String> deleteUserProfileDetailsById(final String userId) {
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }

    @Override
    public ResponseEntity<String> updateUserProfileDetailsById(final String userId, final UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.updateUserProfileDetailsById(userId, updateUserRequest));
    }
}
