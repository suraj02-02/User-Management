package com.bridge.skill.usermanagement.controller;

import com.bridge.skill.usermanagement.dto.request.UserRequestDto;
import com.bridge.skill.usermanagement.dto.response.UserProfileResponseDetailDTO;
import com.bridge.skill.usermanagement.dto.response.UserResponseDto;
import com.bridge.skill.usermanagement.service.intf.UserService;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.bridge.skill.usermanagement.constants.UserConstants.*;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping(USER)
public class UserController {

    private final UserService userService;

    /**
     * Invoke this endpoint to register a new user in system.
     * @param userRequestDto user Info
     * @return user response
     */
    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody @Validated UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.createUser(userRequestDto), HttpStatus.CREATED);
    }

    /**
     * Invoke this endpoint to fetch user details using <code>userId</code>
     * @param userId id of user in system
     * @return user profileDetailed Info
     */
    @GetMapping(USER_ID)
    public ResponseEntity<UserProfileResponseDetailDTO> getUserProfileDetailsById(@PathVariable final String userId) {
        return new ResponseEntity<>(userService.retrieveUserDetailsById(userId) , HttpStatus.OK);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<String> loginUser(@RequestParam @NotBlank String userName, @RequestParam @NotBlank String password) {
        boolean authenticated = userService.authenticateUser(userName, password);
        if (authenticated) {
            return ResponseEntity.ok("Login Successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not valid credentials");
    }

    /**
     * Invoke this endpoint to delete user details using <code>userId</code>
     * @param userId id of user in system
     * @return response message
     */
    @DeleteMapping(USER_ID)
    public ResponseEntity<String> deleteUserProfileDetailsById(@PathVariable final String userId) {
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }









}
