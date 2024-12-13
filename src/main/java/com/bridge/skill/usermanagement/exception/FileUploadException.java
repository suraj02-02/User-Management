package com.bridge.skill.usermanagement.exception;

/**
 * <p>Exception will be thrown when file / Docs upload fails</p>
 *
 * @author surajyadav
 */
public class FileUploadException extends RuntimeException {

    public FileUploadException(String message) {
        super(message);
    }
}
