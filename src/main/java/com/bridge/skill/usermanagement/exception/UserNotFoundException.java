package com.bridge.skill.usermanagement.exception;

/**
 * This exception is thrown when user is not found in the system.
 */

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
