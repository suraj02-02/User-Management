package com.bridge.skill.usermanagement.integration.messagingbusclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * This is one of the implementations of <code>MessageEventBus</code>.
 * It uses <code>Kafka</code> as its event bus for publishing and consuming events
 */

@Slf4j
@Service
public class KafkaEventCable implements MessageEventBus {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaEventCable(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishEvent(Object event) {

        final CompletableFuture<SendResult<String, Object>> future = this.kafkaTemplate.send("test", event);
        future.whenComplete((result , ex) -> {
            if (ex != null) {
                log.error("Error while publishing event to kafka", ex);
            }else {
                log.info("Event published to kafka successfully");
            }
        });
    }

    @Override
    //@KafkaListener(topics = "test", groupId = "group_id")
    public Object consumeEvent() {
        return null;
    }

}
