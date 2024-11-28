package com.bridge.skill.usermanagement.constants.enums;

/**
 * Event type enum which is used for event publishing.
 * Each type is a specific event which will be published and processed by upstream service
 */

public enum UserManagementEventType {

    USER_REGISTRATION_EVENT,
    FORGOT_PASSWORD_EVENT,
    RESET_PASSWORD_EVENT,
    OTP_VERIFICATION_EVENT
}
