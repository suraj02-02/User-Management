package com.bridge.skill.usermanagement.dto.model;

import com.bridge.skill.usermanagement.constants.enums.UserType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class UserRegistrationEventRequest implements Serializable {

    private String userId;
    private String userName;
    private UserType userType;
    private String email;
    private String countryCode;
    private String mobileNumber;
}
