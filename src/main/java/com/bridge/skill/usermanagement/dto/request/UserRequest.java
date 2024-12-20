package com.bridge.skill.usermanagement.dto.request;

import com.bridge.skill.usermanagement.constants.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements Serializable {

    @NotBlank
    private String name;

    @NotBlank
    @Email(message = "Invalid format for email")
    private String email;
    @NotBlank
    private String countryCode;
    @NotBlank
    private String mobileNumber;

    @NotBlank
    private String password;

    @NotNull
    private UserType userType;
}
