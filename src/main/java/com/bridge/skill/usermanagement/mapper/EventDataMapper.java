package com.bridge.skill.usermanagement.mapper;

import com.bridge.skill.usermanagement.dto.model.UserRegistrationEventData;
import com.bridge.skill.usermanagement.entities.User;

import java.util.function.Function;

/**
 * This is common mapper class for all event data mapping/transformation
 */

public class EventDataMapper {

      public static Function<User, UserRegistrationEventData> userToUserRegistrationEventData =
              user -> UserRegistrationEventData.builder()
                      .userId(user.getId())
                      .email(user.getEmail())
                      .mobileNumber(user.getMobileNumber())
                      .countryCode(user.getCountryCode())
                      .userType(user.getUserType())
                      .build();

}
