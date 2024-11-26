package com.bridge.skill.usermanagement.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class UserProfileDetailResponse implements Serializable {

    private UserGeneralInfoResponse userGeneralInfoResponseDTO;
    private UserExperienceInfoResponse userExperienceInfoResponse;
    private List<UserSkillsInfoResponse> userSkillsInfoResponse;
}