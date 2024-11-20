package com.bridge.skill.usermanagement.dto.response;

import com.bridge.skill.usermanagement.constants.enums.SkillProficiency;
import com.bridge.skill.usermanagement.constants.enums.UserSkills;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSkillsInfoResponse {

    private UserSkills skill;
    private SkillProficiency proficiency;
}
