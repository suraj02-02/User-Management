package com.bridge.skill.usermanagement.integration.messagingbusclient;

import com.bridge.skill.usermanagement.constants.enums.UserManagementEventType;

/**
 * This is an interface for message broker client.
 * Message broker will have the events published and will be consumed by respective services
 */

public interface MessageEventBus {

    /**
     * Method is used to publish event data to message broker configured.
     * @param eventData Data to be pushed
     * @param eventType Type of event
     */
    void publishEvent(final Object eventData , final UserManagementEventType eventType);

    /**
     * Method is used to consume event from message broker configured
     * @return event
     */
    Object consumeEvent();
}
