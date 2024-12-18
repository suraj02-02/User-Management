package com.bridge.skill.usermanagement.dto.response;

import com.bridge.skill.usermanagement.constants.enums.UserType;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {

    private String id;
    private String name;
    private String email;
    private String countryCode;
    private String mobileNumber;
    private String profilePictureUrl;
    private UserType userType;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
