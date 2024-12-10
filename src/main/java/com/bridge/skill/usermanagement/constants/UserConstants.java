package com.bridge.skill.usermanagement.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConstants {

    /***** API constants *******/
    public static final String USER = "/users";
    public static final String USER_ID = "/{userId}";
    public static final String LOGIN = "/login";

    /******* Exception constants ******/
    public static final String USER_NOT_FOUND_WITH_ID = "User not found with id : ";

    /******* Response Messages constants ******/
    public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully with id : ";
    public static final String USER_UPDATED_SUCCESSFULLY = "User updated successfully with id : ";
    public static final String FILE_UPLOADED_SUCCESSFULLY = "File uploaded successfully with name : ";

    /******* Pattern constants ******/
    public static final String DATE_PATTERN = "dd/MM/yyyy HH:mm:ss";
}
