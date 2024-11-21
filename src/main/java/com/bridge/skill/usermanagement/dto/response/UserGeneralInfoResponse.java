package com.bridge.skill.usermanagement.dto.response;

import com.bridge.skill.usermanagement.constants.enums.UserType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserGeneralInfoResponse implements Serializable {

    private String id;
    private String name;
    private String email;
    private UserType userType;
    private String profilePictureUrl;
}
