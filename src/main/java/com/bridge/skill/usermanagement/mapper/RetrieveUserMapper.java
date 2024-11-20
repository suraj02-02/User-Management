package com.bridge.skill.usermanagement.mapper;

import com.bridge.skill.usermanagement.dto.response.*;
import com.bridge.skill.usermanagement.entities.Experience;
import com.bridge.skill.usermanagement.entities.Skills;
import com.bridge.skill.usermanagement.entities.User;
import com.bridge.skill.usermanagement.entities.UserSkillDetail;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * This class for conversion of <code>User</code> object to required user response object
 */
public class RetrieveUserMapper {

    /**
     * Method converts <code>User</code> , <code>Skills</code> and <code>Experience</code> details to <code>UserProfileResponseDetailDTO</code>
     * @param user user
     * @param skills skills
     * @param experience experience
     * @return user profile details
     */
    public static UserProfileDetailResponse convertProvidedUserInfoToUserDetailsResponse(User user, Skills skills, Experience experience) {

        return new UserProfileDetailResponse(
                convertUserToUserGeneralInfoResponse.apply(user),
                convertExperienceToUserExperienceInfoResponse.apply(experience),
                generateUserSkillResponseDTO(skills)
        );
    }

    /**
     * Mapper to convert <code>User</code> to <code>UserGeneralInfoResponse</code>
     */
    public static final Function<User, UserGeneralInfoResponse> convertUserToUserGeneralInfoResponse =
            userInfo -> Optional.ofNullable(userInfo)
                    .map(user ->
                            new UserGeneralInfoResponse(
                                    user.getId(),
                                    user.getName(),
                                    user.getEmail(),
                                    user.getUserType(),
                                    user.getProfilePictureUrl()
                            )
                    ).orElse(null);

    /**
     * Mapper to convert <code>Experience</code> to <code>UserExperienceInfoResponseDTO</code>
     */
    public static final Function<Experience , UserExperienceInfoResponse> convertExperienceToUserExperienceInfoResponse =
            experienceInfo -> Optional.ofNullable(experienceInfo)
                    .map(experience ->
                            new UserExperienceInfoResponse(
                                    experience.getJobExperience(),
                                    experience.getEducationDetails()
                            )
                    ).orElse(null);


    /**
     * Method converts <code>Skills</code> to <code>UserSkillsInfoResponseDTO</code>
     * @param skills
     * @return
     */
    private static List<UserSkillsInfoResponse> generateUserSkillResponseDTO(Skills skills) {
        return Optional.ofNullable(skills)
                .map(Skills::getSkills)
                .orElse(Collections.emptySet())
                .stream()
                .filter(Objects::nonNull)
                .map(RetrieveUserMapper::convertSkillsToUserSkillsInfoResponseDTO)
                .toList();
    }

    /**
     * Method converts <code>UserSkillDetail</code> to <code>UserSkillsInfoResponseDTO</code>
     * @param userSkillDetail
     * @return
     */
    public static final UserSkillsInfoResponse convertSkillsToUserSkillsInfoResponseDTO(final UserSkillDetail userSkillDetail) {
        return UserSkillsInfoResponse.builder()
                .skill(userSkillDetail.getSkill())
                .proficiency(userSkillDetail.getProficiency())
                .build();

    }

}
