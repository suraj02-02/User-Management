package com.bridge.skill.usermanagement.mapper;

import com.bridge.skill.usermanagement.dto.model.UserRegistrationEventRequest;
import com.bridge.skill.usermanagement.entities.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

/**
 * This is common mapper class for all event data mapping/transformation
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventDataMapper {

      public static final Function<User, UserRegistrationEventRequest> userToUserRegistrationEventData =
              user -> UserRegistrationEventRequest.builder()
                      .userId(user.getId())
                      .email(user.getEmail())
                      .userName(user.getName())
                      .mobileNumber(user.getMobileNumber())
                      .countryCode(user.getCountryCode())
                      .userType(user.getUserType())
                      .build();

}
