package com.bridge.skill.usermanagement.dto.response;

import com.bridge.skill.usermanagement.constants.enums.SkillProficiency;
import com.bridge.skill.usermanagement.constants.enums.UserSkills;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserSkillsInfoResponse implements Serializable {

    private UserSkills skill;
    private SkillProficiency proficiency;
}
