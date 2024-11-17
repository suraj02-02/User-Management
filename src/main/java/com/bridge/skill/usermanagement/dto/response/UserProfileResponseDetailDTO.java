package com.bridge.skill.usermanagement.dto.response;

import java.util.List;

public record UserProfileResponseDetailDTO(
        UserGeneralInfoResponseDTO userGeneralInfo,
        UserExperienceInfoResponseDTO userExperience,
        List<UserSkillsInfoResponseDTO> userSkills
){
}
