package com.bridge.skill.usermanagement.dto.response;

import com.bridge.skill.usermanagement.constants.enums.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String id;

    private String name;

    private String email;

    private String profilePictureUrl;

    private UserType userType;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;
}
