package com.bridge.skill.usermanagement.dto.response;

import com.bridge.skill.usermanagement.constants.enums.UserType;

public record UserGeneralInfoResponse(
        String id,
        String name,
        String email,
        UserType userType,
        String profilePictureUrl
) {
}
