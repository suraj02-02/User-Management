package com.bridge.skill.usermanagement.dto.response;

import com.bridge.skill.usermanagement.constants.enums.SkillProficiency;
import com.bridge.skill.usermanagement.constants.enums.UserSkills;

public record UserSkillsInfoResponseDTO(
    UserSkills skill,
    SkillProficiency proficiency
) {
}
