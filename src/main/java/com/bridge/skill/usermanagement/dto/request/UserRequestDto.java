package com.bridge.skill.usermanagement.dto.request;

import com.bridge.skill.usermanagement.constants.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDto(String id,
                             @NotBlank String name,
                             @NotBlank @Email(message = "Invalid format for email") String email,
                             @NotBlank String password,
                             String profilePictureUrl,
                             @NotNull UserType userType
                            ) {
}
