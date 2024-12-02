package com.bridge.skill.usermanagement.integration.messagingbusclient;

import com.bridge.skill.usermanagement.config.CableEventTypeConfig;
import com.bridge.skill.usermanagement.constants.enums.UserManagementEventType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * This is one of the implementations of <code>MessageEventBus</code>.
 * It uses <code>Kafka</code> as its event bus for publishing and consuming events
 */

@Slf4j
@Service
@AllArgsConstructor
public class KafkaEventCable implements MessageEventBus {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CableEventTypeConfig cableEventTypeConfig;

    @Override
    public void publishEvent(final String event, final UserManagementEventType eventType) {

        final String topicName = this.cableEventTypeConfig.getTopicBasedOnEvent(eventType);
        this.kafkaTemplate.send(topicName , event).whenComplete((result , ex) -> {
            if (ex != null) {
                log.error("Error occurred while publishing event to kafka due to : {}", ex.getMessage());
            }else {
                log.info("Event published to kafka successfully on topic : {}" , result.getRecordMetadata().topic());
            }
        });
    }

    @Override
    //@KafkaListener(topics = "test", groupId = "group_id")
    public Object consumeEvent() {
        log.error("Operation not supported as of now!");
        return null;
    }

}
