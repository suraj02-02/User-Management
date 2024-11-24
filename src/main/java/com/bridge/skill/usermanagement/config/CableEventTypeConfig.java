package com.bridge.skill.usermanagement.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "cable")
public class CableEventTypeConfig {

    private Map<String , String> eventTypes;

    /**
     * Method is used to get the topic based on the event type as per the configuration
     * @param event
     * @return topic Name
     */
    public String getTopicBasedOnEvent(String event) {
        if(CollectionUtils.isEmpty(eventTypes)) {
            return null;
        }
        return eventTypes.get(event);
    }
}
