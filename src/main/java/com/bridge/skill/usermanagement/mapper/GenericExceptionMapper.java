package com.bridge.skill.usermanagement.mapper;

import com.bridge.skill.usermanagement.dto.response.GenericExceptionResponse;
import com.bridge.skill.usermanagement.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.function.Function;

public class GenericExceptionMapper {

    /**
     *  Transforms the <code>UserNotFoundException</code> exception to <code>GenericExceptionResponse</code> for client exception response
     */
    public static final Function<UserNotFoundException , GenericExceptionResponse> convertUserNotFoundExceptionToGenericExceptionResponse =
            userNotFoundException -> GenericExceptionResponse.builder()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .exceptionMessage(userNotFoundException.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build();
    /**
     *  Transforms the <code>Exception</scode> exception to <code>GenericExceptionResponse</code> for client exception response
     */
    public static final Function<Exception , GenericExceptionResponse> convertExceptionToGenericExceptionResponse =
            exception -> GenericExceptionResponse.builder()
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .exceptionMessage(exception.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build();
}
