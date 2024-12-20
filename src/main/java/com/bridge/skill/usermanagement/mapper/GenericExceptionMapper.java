package com.bridge.skill.usermanagement.mapper;

import com.bridge.skill.usermanagement.dto.response.GenericExceptionResponse;
import com.bridge.skill.usermanagement.exception.FileUploadException;
import com.bridge.skill.usermanagement.exception.UserNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.util.InvalidMimeTypeException;

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
     *  Transforms the <code>FileSizeLimitExceededException</code> exception to <code>GenericExceptionResponse</code> for client exception response
     */
    public static final Function<FileSizeLimitExceededException, GenericExceptionResponse> convertFileSizeLimitExceededExceptionToGenericExceptionResponse =
            fileSizeLimitException -> GenericExceptionResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .exceptionMessage(fileSizeLimitException.getMessage())
                    .timestamp(LocalDateTime.now())
                    .build();

    /**
     *  Transforms the <code>InvalidMimeTypeException</code> exception to <code>GenericExceptionResponse</code> for client exception response
     */
    public static final Function<InvalidMimeTypeException, GenericExceptionResponse> convertInvalidMimeTypeExceptionToGenericExceptionResponse =
            invalidMimeTypeException -> GenericExceptionResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .exceptionMessage(invalidMimeTypeException.getMessage())
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
