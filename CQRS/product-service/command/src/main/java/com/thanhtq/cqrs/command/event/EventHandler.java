package com.thanhtq.cqrs.command.event;

import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

public interface EventHandler {
    void handler(@Payload List<String> productEvents, Acknowledgment acknowledgment);
    void handler(@Payload String productEvent, Acknowledgment acknowledgment);
}
