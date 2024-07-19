package com.thanhtq.cqrs.command.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductEventHandler implements EventHandler {

    @Override
    @KafkaListener(
            topics = "product-topic",
            groupId = "product-command-consumer-group",
            concurrency = "1",
            batch = "true"
    )
    public void handler(@Payload List<String> productEvents, Acknowledgment acknowledgment) {
        // Save event to store
        acknowledgment.acknowledge();
    }

    @Override
    public void handler(String productEvent, Acknowledgment acknowledgment) {

    }
}
