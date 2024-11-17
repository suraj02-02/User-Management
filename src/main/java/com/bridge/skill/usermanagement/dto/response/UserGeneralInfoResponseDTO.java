package com.bridge.skill.usermanagement.dto.response;

import com.bridge.skill.usermanagement.constants.enums.UserType;

public record UserGeneralInfoResponseDTO(
        String id,
        String name,
        String email,
        UserType userType,
        String profilePictureUrl
) {
}
