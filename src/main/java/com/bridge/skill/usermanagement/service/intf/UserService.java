package com.bridge.skill.usermanagement.service.intf;

import com.bridge.skill.usermanagement.dto.request.UserRequestDto;
import com.bridge.skill.usermanagement.dto.response.UserProfileResponseDetailDTO;
import com.bridge.skill.usermanagement.dto.response.UserResponseDto;

public interface UserService {

    /**
     * Method is user to create user in the system having required info in the request <code>userRequestDto</code>
     * @param userRequestDto user info
     * @return user response
     */

    UserResponseDto createUser(final UserRequestDto userRequestDto);

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
    UserProfileResponseDetailDTO retrieveUserDetailsById(final String userId);

    /**
     * Method is used to delete user details using <code>userId</code>
     * @param userId user id
     * @return response message
     */
    String deleteUserById(final String userId);
}
