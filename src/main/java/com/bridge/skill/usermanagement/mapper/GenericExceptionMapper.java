package com.bridge.skill.usermanagement.mapper;

import com.bridge.skill.usermanagement.dto.response.GenericExceptionResponse;
import com.bridge.skill.usermanagement.exception.FileUploadException;
import com.bridge.skill.usermanagement.exception.UserNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
     *  Transforms the <code>UserNotFoundException</code> exception to <code>GenericExceptionResponse</code> for client exception response
     */
    public static final Function<FileUploadException, GenericExceptionResponse> convertFileUploadExceptionToGenericExceptionResponse =
            fileUploadException -> GenericExceptionResponse.builder()
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .exceptionMessage(fileUploadException.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build();

    /**
     *  Transforms the <code>IllegalArgumentException</code> exception to <code>GenericExceptionResponse</code> for client exception response
     */
    public static final Function<IllegalArgumentException , GenericExceptionResponse> convertIllegalArgumentExceptionToGenericExceptionResponse =
            illegalArgumentException -> GenericExceptionResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .exceptionMessage(illegalArgumentException.getMessage())
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
