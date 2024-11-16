package com.bridge.skill.usermanagement.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * The {@code User} record represents a user in the system.
 * @param id
 * @param name
 * @param email
 * @param password
 * @param profilePicture
 * @param userType
 * @param createdOn
 * @param updatedOn
 */
@Document(collection = "users")
@Builder
public record User(@Id String id,
                   @NotBlank String name,
                   @NotBlank @Email(message = "Invalid format for email") String email,
                   @NotBlank String password,
                   String profilePicture,
                   @NotNull String userType,
                   @NotNull @CreatedDate Instant createdOn,
                   @NotNull @LastModifiedDate Instant updatedOn) {
}
