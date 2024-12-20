package com.bridge.skill.usermanagement.controller;

import com.bridge.skill.usermanagement.dto.request.UpdateUserRequest;
import com.bridge.skill.usermanagement.dto.request.UserRequest;
import com.bridge.skill.usermanagement.dto.response.UserProfileDetailResponse;
import com.bridge.skill.usermanagement.dto.response.UserResponse;
import jakarta.validation.constraints.NotBlank;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.bridge.skill.usermanagement.constants.UserConstants.*;

@Validated
@RequestMapping(USER)
public interface IUserController {

    /**
     * Invoke this endpoint to register a new user in system.
     * It will take general info of user and file as input which is optional
     *
     * @param userRequest user Info
     * @return user response
     */
    @PostMapping
    ResponseEntity<UserResponse> registerUser(@RequestPart("userRequest") @Validated final UserRequest userRequest,
                                              @RequestPart(value = "file" , required = false) final MultipartFile file) throws FileSizeLimitExceededException;

    /**
     * Invoke this endpoint to fetch user details using <code>userId</code>
     * @param userId id of user in system
     * @return user profileDetailed Info
     */
    @GetMapping(USER_ID)
    ResponseEntity<UserProfileDetailResponse> getUserProfileDetailsById(@PathVariable String userId);

    @PostMapping(LOGIN)
    ResponseEntity<String> loginUser(@RequestParam @NotBlank String userName, 
                                     @RequestParam @NotBlank String password);

    /**
     * Invoke this endpoint to delete user details using <code>userId</code>
     * @param userId id of user in system
     * @return response message
     */
    @DeleteMapping(USER_ID)
    ResponseEntity<String> deleteUserProfileDetailsById(@PathVariable String userId);

    /**
     * Invoke this endpoint to update user details using <code>userId</code>
     * @param userId id of user in system
     * @param updateUserRequest updated user data
     * @return response message
     */
    @PutMapping(USER_ID)
    ResponseEntity<String> updateUserProfileDetailsById(@PathVariable String userId, 
                                                        @RequestBody UpdateUserRequest updateUserRequest);

}
