package com.bridge.skill.usermanagement.entities;

import com.bridge.skill.usermanagement.constants.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * The {@code User} represents a user in the system.
 *
 */
@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    @Email(message = "Invalid format for email")
    private String email;

    @NotBlank
    private String password;

    private String profilePictureUrl;

    @NotNull
    private UserType userType;

    @CreatedDate
    @NotNull
    private Instant createdOn;

    @LastModifiedDate
    @NotNull
    private Instant updatedOn;
}
