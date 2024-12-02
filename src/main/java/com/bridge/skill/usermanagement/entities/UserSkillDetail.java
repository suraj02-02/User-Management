package com.bridge.skill.usermanagement.entities;

import com.bridge.skill.usermanagement.constants.enums.SkillProficiency;
import com.bridge.skill.usermanagement.constants.enums.UserSkills;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserSkillDetail implements Serializable {

    @NotBlank(message = "Skill is required")
    private UserSkills skill;
    @NotBlank(message = "Proficiency is required")
    private SkillProficiency proficiency;
}
