package com.bridge.skill.usermanagement.mapper;

import com.bridge.skill.usermanagement.dto.response.*;
import com.bridge.skill.usermanagement.entities.Experience;
import com.bridge.skill.usermanagement.entities.Skills;
import com.bridge.skill.usermanagement.entities.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * This class for conversion of <code>User</code> object to required user response object
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RetrieveUserMapper {

    /**
     * Method converts <code>User</code> , <code>Skills</code> and <code>Experience</code> details to <code>UserProfileResponseDetailDTO</code>
     * @param user user
     * @param skills skills
     * @param experience experience
     * @return user profile details
     */
    public static UserProfileDetailResponse convertUserInfoTOUserProfileDetailResponse(final User user,
                                                                                       final Skills skills,
                                                                                       final Experience experience) {

        return UserProfileDetailResponse.builder()
                .userGeneralInfoResponseDTO(convertUserToUserGeneralInfoResponse.apply(user))
                .userExperienceInfoResponse(convertExperienceToUserExperienceInfoResponse.apply(experience))
                .userSkillsInfoResponse(convertSkillsToUserSkillsInfoResponse.apply(skills))
                .build();
    }

    /**
     * Mapper to convert <code>User</code> to <code>UserGeneralInfoResponse</code>
     */
    public static final Function<User, UserGeneralInfoResponse> convertUserToUserGeneralInfoResponse =
            userInfo -> Optional.ofNullable(userInfo)
                    .map(user -> UserGeneralInfoResponse.builder()
                                            .id(user.getId())
                                            .name(user.getName())
                                            .email(user.getEmail())
                                            .userType(user.getUserType())
                                         //   .profilePictureUrl(user.getProfilePictureUrl())
                                            .build()
                    ).orElse(null);

    /**
     * Mapper to convert <code>Experience</code> to <code>UserExperienceInfoResponseDTO</code>
     */
    public static final Function<Experience , UserExperienceInfoResponse> convertExperienceToUserExperienceInfoResponse =
            experienceInfo -> Optional.ofNullable(experienceInfo)
                         .map(experience -> UserExperienceInfoResponse.builder()
                                .jobExperienceDetails(experience.getJobExperienceDetails())
                                .educationalExperienceDetails(experience.getEducationalExperienceDetails())
                                .build()
                         ).orElse(null);


    /**
     * Method converts <code>Skills</code> to <code>UserSkillsInfoResponseDTO</code>
     * @param skills
     * @return
     */
    private static final Function<Skills, List<UserSkillsInfoResponse>> convertSkillsToUserSkillsInfoResponse =
    skills -> Optional.ofNullable(skills)
            .map(Skills::getSkills)
            .orElse(Collections.emptySet())
            .stream()
            .filter(Objects::nonNull)
            .map(skill -> UserSkillsInfoResponse.builder()
                        .skill(skill.getSkill())
                        .proficiency(skill.getProficiency())
                        .build()
            ).toList();

}
