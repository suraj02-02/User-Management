package com.bridge.skill.usermanagement.integration.messagingbusclient;

/**
 * This is an interface for message broker client.
 * Message broker will have the events published and will be consumed by respective services
 *
 * TODO check to make event bus with generics
 */
public interface MessageEventBus {

    /**
     * Method is used to publish event to message broker configured
     * @param event
     */
    void publishEvent(final Object event);

    /**
     * Method is used to consume event from message broker configured
     * @return
     */
    Object consumeEvent();
}
