package com.bridge.skill.usermanagement.entities;

import com.bridge.skill.usermanagement.constants.enums.UserType;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * The {@code User} represents a user in the system.
 */

@Builder
@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class User {

    @Id
    private String id;
    private String name;
    @Email(message = "Invalid format for email")
    private String email;
    private String countryCode;
    private String mobileNumber;
    private String password;
    private byte[] profilePicture;
    private UserType userType;
    @CreatedDate
    private LocalDateTime createdOn;
    @LastModifiedDate
    private LocalDateTime updatedOn;
}
