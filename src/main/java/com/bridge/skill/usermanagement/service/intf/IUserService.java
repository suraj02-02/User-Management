package com.bridge.skill.usermanagement.service.intf;

import com.bridge.skill.usermanagement.dto.request.UpdateUserRequest;
import com.bridge.skill.usermanagement.dto.request.UserRequest;
import com.bridge.skill.usermanagement.dto.response.UserProfileDetailResponse;
import com.bridge.skill.usermanagement.dto.response.UserResponse;

public interface IUserService {

    /**
     * Method is user to create user in the system having required info in the request <code>userRequestDto</code>
     * @param userRequest user info
     * @return user response
     */

    UserResponse createUser(final UserRequest userRequest);

    /**
     * <p>
     *  Method is used to retrieve user details using <code>userId</code>
     * </p>
     * <p>
     *   User Details in our system is present in :
     *     <ol>
     *         <li>User Entity - General Info of a user</li>
     *         <li>Experience Entity - Experience of a user</li>
     *          <li>Skills Entity - Skills of a user</li>
     *     </ol>
     * </p>
     *
     * @param userId user id
     * @return user profile details
     */
    UserProfileDetailResponse retrieveUserDetailsById(final String userId);


    boolean authenticateUser(String username, String password);

    /**
     * Method is used to delete user details using <code>userId</code>
     * @param userId user id
     * @return response message
     */
    String deleteUserById(final String userId);

    /**
     * Method is used to update user details using <code>userId</code>
     *
     * @param userId user id
     * @param updateUserRequest update user request
     * @return response message
     */
    String updateUserProfileDetailsById(final String userId, UpdateUserRequest updateUserRequest);
}
