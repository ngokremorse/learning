package com.thanhtq.cqrs.query.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

public interface EventHandler {
    void handler(@Payload List<String> productEvents, Acknowledgment acknowledgment) throws JsonProcessingException;
    void handler(@Payload String productEvent, Acknowledgment acknowledgment);
}
