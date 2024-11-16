package com.bridge.skill.usermanagement.service.impl;

import com.bridge.skill.usermanagement.dao.UserRepository;
import com.bridge.skill.usermanagement.dto.User;
import com.bridge.skill.usermanagement.dto.request.UserRequestDto;
import com.bridge.skill.usermanagement.dto.response.UserResponseDto;
import com.bridge.skill.usermanagement.service.intf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
}
