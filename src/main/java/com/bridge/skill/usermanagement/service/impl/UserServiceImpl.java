package com.bridge.skill.usermanagement.service.impl;

import com.bridge.skill.usermanagement.dto.request.UpdateUserRequestDTO;
import com.bridge.skill.usermanagement.dto.request.UserRequestDto;
import com.bridge.skill.usermanagement.dto.response.UserProfileResponseDetailDTO;
import com.bridge.skill.usermanagement.dto.response.UserResponseDto;
import com.bridge.skill.usermanagement.entities.Experience;
import com.bridge.skill.usermanagement.entities.Skills;
import com.bridge.skill.usermanagement.entities.User;
import com.bridge.skill.usermanagement.exception.UserNotFoundException;
import com.bridge.skill.usermanagement.mapper.RetrieveUserMapper;
import com.bridge.skill.usermanagement.mapper.UserMapper;
import com.bridge.skill.usermanagement.model.UserSkillDetail;
import com.bridge.skill.usermanagement.repository.ExperienceRepository;
import com.bridge.skill.usermanagement.repository.SkillsRepository;
import com.bridge.skill.usermanagement.repository.UserRepository;
import com.bridge.skill.usermanagement.service.intf.UserService;
import com.bridge.skill.usermanagement.util.AsyncTaskAcceptor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

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
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user =  userMapper.toUser(userRequestDto);
        User createdUser = userRepository.save(user);
        return userMapper.toUserResponseDto(createdUser);
    }

    @Override
    public UserProfileResponseDetailDTO retrieveUserDetailsById(final String userId) {

        return this.userRepository.findById(userId)
                .map(userInfo -> {
                    // TODO the experience and skills details should be served from cache to avoid multiple db calls , since the data will not be changing frequently
                    final Experience experience = this.experienceRepository.findByUserId(userId);
                    final Skills skills = this.skillsRepository.findByUserId(userId);
                    return RetrieveUserMapper.convertProvidedUserInfoToUserDetailsResponse(userInfo, skills, experience);
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
    public String updateUserProfileDetailsById(String userId, UpdateUserRequestDTO updateUserRequestDTO) {

        /**
         * TODO
         *   1.Ability to update profile picture
         *   2.Provide proper structuring for experience and educational details to properly update
         *   3.Making the update operation atomic
         *   4.Check the api bottleneck
         */
        return Optional.of(this.userRepository.existsById(userId))
                    .map(isUserExists -> {
                        // Updating the user experience details
                        final Experience userExperienceDetail = this.experienceRepository.findByUserId(userId);
                        userExperienceDetail.setJobExperience(updateUserRequestDTO.getJobExperience());
                        userExperienceDetail.setEducationDetails(updateUserRequestDTO.getEducationalExperience());
                        this.experienceRepository.save(userExperienceDetail);

                        // Updating the user skill details
                        final Skills userSkillDetail = this.skillsRepository.findByUserId(userId);
                        Set<UserSkillDetail> userSkillDetails = userSkillDetail.getSkills();
                        userSkillDetails.addAll(updateUserRequestDTO.getSkillDetail());
                        this.skillsRepository.save(userSkillDetail);

                        return USER_UPDATED_SUCCESSFULLY + userId;
                })
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID + userId));
    }


}
