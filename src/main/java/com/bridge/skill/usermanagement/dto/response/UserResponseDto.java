package com.bridge.skill.usermanagement.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record UserResponseDto(String id,
                              String name,
                              String email,
                              String profilePicture,
                              String userType,
                              Instant createdOn,
                              Instant updatedOn) {
}
