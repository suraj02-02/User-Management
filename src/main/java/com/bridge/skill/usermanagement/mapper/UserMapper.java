package com.bridge.skill.usermanagement.mapper;

import com.bridge.skill.usermanagement.dto.request.UserRequestDto;
import com.bridge.skill.usermanagement.dto.response.UserResponseDto;
import com.bridge.skill.usermanagement.entities.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring" )
@Component
public interface UserMapper {

    UserResponseDto toUserResponseDto(User user);

    User toUser(UserRequestDto userRequestDto);

}
