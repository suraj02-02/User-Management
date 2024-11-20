package com.bridge.skill.usermanagement.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserProfileDetailResponse {

    private UserGeneralInfoResponse userGeneralInfoResponseDTO;
    private UserExperienceInfoResponse userExperienceInfoResponse;
    private List<UserSkillsInfoResponse> userSkillsInfoResponse;
}