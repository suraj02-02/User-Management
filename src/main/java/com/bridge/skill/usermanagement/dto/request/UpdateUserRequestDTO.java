package com.bridge.skill.usermanagement.dto.request;

import com.bridge.skill.usermanagement.model.UserSkillDetail;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UpdateUserRequestDTO {

    // TODO check the structuring of experience details
    private String educationalExperience;
    private String jobExperience;
    private Set<UserSkillDetail> skillDetail;
}
