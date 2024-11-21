package com.bridge.skill.usermanagement.service.impl;

import com.bridge.skill.usermanagement.dto.model.EducationalDetails;
import com.bridge.skill.usermanagement.dto.model.JobExperienceDetails;
import com.bridge.skill.usermanagement.dto.request.EducationalDetailsRequest;
import com.bridge.skill.usermanagement.dto.request.JobExperienceDetailsRequest;
import com.bridge.skill.usermanagement.dto.request.UpdateUserRequest;
import com.bridge.skill.usermanagement.dto.request.UserRequest;
import com.bridge.skill.usermanagement.dto.response.UserProfileDetailResponse;
import com.bridge.skill.usermanagement.dto.response.UserResponse;
import com.bridge.skill.usermanagement.entities.*;
import com.bridge.skill.usermanagement.exception.UserNotFoundException;
import com.bridge.skill.usermanagement.mapper.RetrieveUserMapper;
import com.bridge.skill.usermanagement.mapper.UserMapper;
import com.bridge.skill.usermanagement.repository.ExperienceRepository;
import com.bridge.skill.usermanagement.repository.SkillsRepository;
import com.bridge.skill.usermanagement.repository.UserRepository;
import com.bridge.skill.usermanagement.service.intf.UserService;
import com.bridge.skill.usermanagement.util.AsyncTaskAcceptor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

import static com.bridge.skill.usermanagement.constants.UserConstants.*;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ExperienceRepository experienceRepository;
    private final SkillsRepository skillsRepository;
    private final UserMapper userMapper;
    private final AsyncTaskAcceptor asyncTaskAcceptor;

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        User user =  userMapper.toUser(userRequest);
        User createdUser = userRepository.save(user);
        return userMapper.toUserResponseDto(createdUser);
    }

    @Override
    public UserProfileDetailResponse retrieveUserDetailsById(final String userId) {

        return this.userRepository.findById(userId)
                .map(userInfo -> {
                    // TODO the experience and skills details should be served from cache to avoid multiple db calls , since the data will not be changing frequently
                    final Experience experience = this.experienceRepository.findByUserId(userInfo.getId());
                    final Skills skills = this.skillsRepository.findByUserId(userInfo.getId());
                    return RetrieveUserMapper.convertUserInfoTOUserProfileDetailResponse(userInfo, skills, experience);
                })
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID + userId));
    }


    @Override
    public boolean authenticateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByName(username);
        if(userOptional.isEmpty()) {
            return false;
        }
        User user = userOptional.get();
        return user.getPassword().equals(password);
    }

    @Override
    public String deleteUserById(final String userId) {

        return this.userRepository.findById(userId)
                .map(user -> {
                    this.userRepository.deleteById(user.getId());
                    /**** Asynchronous deletion of related documents using virtual
                     **** threads for better performance and responsiveness ******/
                    this.asyncTaskAcceptor.submit(() -> {
                        this.experienceRepository.deleteByUserId(user.getId());
                        this.skillsRepository.deleteByUserId(user.getId());
                    });
                    return USER_DELETED_SUCCESSFULLY + userId;
                })
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID + userId));
    }


    @Override
    @Transactional
    public String updateUserProfileDetailsById(final String userId, final UpdateUserRequest updateUserRequest) {

        /**
         * TODO
         *   1.Ability to update profile picture
         *   2.Provide proper structuring for experience and educational details to properly update -- Done
         *   3.Making the update operation atomic
         *   4.Check the api bottleneck
         */
        return Optional.of(this.userRepository.existsById(userId))
                    .map(isUserExists -> {
                        /*** Updating user experience details ****/
                        Experience experience = this.experienceRepository.findByUserId(userId);
                        if(experience == null) {
                            experience = Experience.builder().userId(userId).build();
                        }

                       populateUserEducationalExperienceDetails(updateUserRequest.getEducationalExperienceDetails() , experience);
                       populateUserJobExperienceDetails(updateUserRequest.getJobExperienceDetails() , experience);
                       this.experienceRepository.save(experience);

                       /**** Updating user skill details *****/
                        Skills skill = this.skillsRepository.findByUserId(userId);
                        if (skill == null) {
                            skill = Skills.builder().userId(userId).build();
                        }

                        skill.setSkills(updateUserRequest.getSkillDetail());
                        this.skillsRepository.save(skill);
                        return USER_UPDATED_SUCCESSFULLY + userId;
                })
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID + userId));
    }

    /**
     * Method is used to populate the user educational experience details from the request <code>educationalDetailsRequestDTO</code>
     * to the <code>experience</code> document
     * @param educationalDetailsRequest educational details Request
     * @param experience experience Document
     */
    private void populateUserEducationalExperienceDetails(final List<EducationalDetailsRequest> educationalDetailsRequest,
                                                          final Experience experience) {

        Optional.ofNullable(educationalDetailsRequest)
                .filter(obj -> !CollectionUtils.isEmpty(obj))
                .map(obj -> obj.stream()
                        .map(educationalDetailEach -> EducationalDetails.builder()
                            .instituteName(educationalDetailEach.getInstituteName())
                            .specialization(educationalDetailEach.getSpecialization())
                            .startYear(educationalDetailEach.getStartYear())
                            .endYear(educationalDetailEach.getEndYear())
                            .build()
                        ).toList()
                ).ifPresent(experience::setEducationalExperienceDetails);
    }

    /**
     * Method is used to populate the user job experience details from the request <code>jobExperienceDetailsRequestDTO</code>
     * to the <code>experience</code> document
     * @param jobExperienceDetails job experience details Request
     * @param experience experience Document
     */
    private void populateUserJobExperienceDetails(final List<JobExperienceDetailsRequest> jobExperienceDetails ,
                                                  final Experience experience) {

        Optional.ofNullable(jobExperienceDetails)
                .filter(obj -> !CollectionUtils.isEmpty(obj))
                .map(obj -> obj.stream()
                        .map(jobDetailEach -> JobExperienceDetails.builder()
                                .companyName(jobDetailEach.getCompanyName())
                                .designation(jobDetailEach.getDesignation())
                                .startYear(jobDetailEach.getStartYear())
                                .endYear(jobDetailEach.getEndYear())
                                .build()
                        ).toList()
                ).ifPresent(experience::setJobExperienceDetails);
    }




}
