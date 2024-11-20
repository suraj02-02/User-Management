package com.bridge.skill.usermanagement.entities;

import com.bridge.skill.usermanagement.constants.enums.SkillProficiency;
import com.bridge.skill.usermanagement.constants.enums.UserSkills;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserSkillDetail implements Serializable {

    private UserSkills skill;
    private SkillProficiency proficiency;
}
