package com.bridge.skill.usermanagement.model;

import com.bridge.skill.usermanagement.constants.enums.SkillProficiency;
import com.bridge.skill.usermanagement.constants.enums.UserSkills;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserSkillDetail {
    private UserSkills skill;
    private SkillProficiency proficiency;
}
