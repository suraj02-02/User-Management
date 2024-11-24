package com.bridge.skill.usermanagement.entities;

import com.bridge.skill.usermanagement.dto.model.EducationalDetails;
import com.bridge.skill.usermanagement.dto.model.JobExperienceDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * The {@code Experience} represents the experience of a user in the system.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "experience")
@Builder
public class Experience {

    @Id
    private String id;
    private String userId;
    private List<EducationalDetails> educationalExperienceDetails;
    private List<JobExperienceDetails> jobExperienceDetails;
}
