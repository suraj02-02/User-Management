package com.bridge.skill.usermanagement.config;

import com.bridge.skill.usermanagement.constants.enums.UserManagementEventType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "messaging.cable")
public class CableEventTypeConfig {

    private Map<UserManagementEventType, String> eventTypes;

    /**
     * Method is used to get the topic based on the event type as per the configuration
     * @param event Event Type
     * @return topic Name
     */
    public String getTopicBasedOnEvent(UserManagementEventType event) {
        if(CollectionUtils.isEmpty(this.eventTypes)) {
            return null;
        }
        return this.eventTypes.get(event);
    }
}
