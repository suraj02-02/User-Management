package com.bridge.skill.usermanagement.service.impl;

import com.bridge.skill.usermanagement.dto.response.UserProfileResponseDetailDTO;
import com.bridge.skill.usermanagement.entities.Experience;
import com.bridge.skill.usermanagement.entities.Skills;
import com.bridge.skill.usermanagement.entities.User;
import com.bridge.skill.usermanagement.exception.UserNotFoundException;
import com.bridge.skill.usermanagement.mapper.RetrieveUserMapper;
import com.bridge.skill.usermanagement.mapper.UserMapper;
import com.bridge.skill.usermanagement.repository.ExperienceRepository;
import com.bridge.skill.usermanagement.repository.SkillsRepository;
import com.bridge.skill.usermanagement.repository.UserRepository;
import com.bridge.skill.usermanagement.dto.request.UserRequestDto;
import com.bridge.skill.usermanagement.dto.response.UserResponseDto;
import com.bridge.skill.usermanagement.service.intf.UserService;
import com.bridge.skill.usermanagement.util.AsyncTaskAcceptor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.bridge.skill.usermanagement.constants.UserConstants.USER_DELETED_SUCCESSFULLY;
import static com.bridge.skill.usermanagement.constants.UserConstants.USER_NOT_FOUND_WITH_ID;

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
    @Transactional
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

}
