package com.bridge.skill.usermanagement.exception;

import com.bridge.skill.usermanagement.mapper.GenericExceptionMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class is used to handle exceptions thrown in the application
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

      /***** Exception handler for <code>UserNotFoundException</code> ******/
      @ExceptionHandler(UserNotFoundException.class)
      public ResponseEntity<GenericExceptionResponse> handleUserNotFoundException(final UserNotFoundException exception) {
          return new ResponseEntity<>(GenericExceptionMapper.convertUserNotFoundExceptionToGenericExceptionResponse.apply(exception) , HttpStatus.NOT_FOUND);
      }

    /***** Exception handler for <code>Exception</code> ******/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericExceptionResponse> handleInternalSererError(final Exception exception) {
        return new ResponseEntity<>(GenericExceptionMapper.convertExceptionToGenericExceptionResponse.apply(exception) , HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
