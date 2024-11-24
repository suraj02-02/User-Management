package com.bridge.skill.usermanagement.dto.response;

import com.bridge.skill.usermanagement.dto.model.EducationalDetails;
import com.bridge.skill.usermanagement.dto.model.JobExperienceDetails;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class UserExperienceInfoResponse implements Serializable {

    private List<EducationalDetails> educationalExperienceDetails;
    private List<JobExperienceDetails> jobExperienceDetails;
}