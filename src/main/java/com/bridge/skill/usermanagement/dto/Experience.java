package com.bridge.skill.usermanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The {@code Experience} represents the experience of a user in the system.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Experience {

    @Id
    private String id;

    @NotBlank
    private String userId;

    @NotBlank
    private String educationDetails;

    @NotBlank
    private String jobExperience;

}
