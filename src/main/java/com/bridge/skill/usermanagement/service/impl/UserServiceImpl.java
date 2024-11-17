package com.bridge.skill.usermanagement.service.impl;

import com.bridge.skill.usermanagement.dto.response.UserProfileResponseDetailDTO;
import com.bridge.skill.usermanagement.entities.Experience;
import com.bridge.skill.usermanagement.entities.Skills;
import com.bridge.skill.usermanagement.entities.User;
import com.bridge.skill.usermanagement.exception.UserNotFoundException;
import com.bridge.skill.usermanagement.mapper.RetrieveUserMapper;
import com.bridge.skill.usermanagement.repository.ExperienceRepository;
import com.bridge.skill.usermanagement.repository.SkillsRepository;
import com.bridge.skill.usermanagement.repository.UserRepository;
import com.bridge.skill.usermanagement.dto.request.UserRequestDto;
import com.bridge.skill.usermanagement.dto.response.UserResponseDto;
import com.bridge.skill.usermanagement.service.intf.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bridge.skill.usermanagement.constants.UserConstants.USER_NOT_FOUND_WITH_ID;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ExperienceRepository experienceRepository;
    private final SkillsRepository skillsRepository;

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user =  new User();
        user.setName(userRequestDto.name());
        user.setEmail(userRequestDto.email());
        user.setUserType(userRequestDto.userType());
        user.setProfilePictureUrl(userRequestDto.profilePictureUrl());
        user.setPassword(userRequestDto.password());
        userRepository.save(user);
        return null;
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




}
