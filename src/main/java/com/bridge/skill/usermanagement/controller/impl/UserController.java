package com.bridge.skill.usermanagement.controller.impl;

import com.bridge.skill.usermanagement.controller.IUserController;
import com.bridge.skill.usermanagement.dto.request.UpdateUserRequest;
import com.bridge.skill.usermanagement.dto.request.UserRequest;
import com.bridge.skill.usermanagement.dto.response.UserProfileDetailResponse;
import com.bridge.skill.usermanagement.dto.response.UserResponse;
import com.bridge.skill.usermanagement.service.intf.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController implements IUserController {

    private final IUserService userService;

    @Override
    public ResponseEntity<UserResponse> registerUser(UserRequest userRequest) {
        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
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
