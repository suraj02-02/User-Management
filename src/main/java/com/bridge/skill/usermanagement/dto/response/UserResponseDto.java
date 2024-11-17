package com.bridge.skill.usermanagement.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.Instant;
import java.time.LocalDateTime;

public record UserResponseDto(String id,
                              String name,
                              String email,
                              String profilePicture,
                              String userType,
                              LocalDateTime createdOn,
                              LocalDateTime updatedOn) {
}
