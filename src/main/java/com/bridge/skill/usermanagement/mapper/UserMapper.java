package com.bridge.skill.usermanagement.mapper;

import com.bridge.skill.usermanagement.dto.request.UserRequest;
import com.bridge.skill.usermanagement.dto.response.UserResponse;
import com.bridge.skill.usermanagement.entities.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring" )
@Component
public interface UserMapper {

    UserResponse toUserResponseDto(User user);
    User toUser(UserRequest userRequest);
}
