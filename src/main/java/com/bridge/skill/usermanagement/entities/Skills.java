package com.bridge.skill.usermanagement.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * The {@code Skills} represents skills of a user in the system.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "skills")
@Builder
public class Skills {

    @Id
    private String id;

    @NotBlank
    private String userId;

    @NotNull
    private Set<UserSkillDetail> skills;
}
