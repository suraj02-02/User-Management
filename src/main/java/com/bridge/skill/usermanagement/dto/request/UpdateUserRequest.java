package com.bridge.skill.usermanagement.dto.request;

import com.bridge.skill.usermanagement.entities.UserSkillDetail;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class UpdateUserRequest implements Serializable {

    private List<EducationalDetailsRequest> educationalExperienceDetails;
    private List<JobExperienceDetailsRequest> jobExperienceDetails;
    private Set<UserSkillDetail> skillDetail;
}
