package com.bridge.skill.usermanagement.dto.request;

import com.bridge.skill.usermanagement.constants.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    private String id;
    @NotBlank
    private String name;

    @NotBlank
    @Email(message = "Invalid format for email")
    private String email;

    @NotBlank
    private String password;

    String profilePictureUrl;

    @NotNull
    private UserType userType;

}
