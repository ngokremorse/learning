package com.thanhtq.cqrs.event;

import org.springframework.boot.autoconfigure.jms.AcknowledgeMode;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductEventHandler {



    @KafkaListener(
            topics = "product-topic",
            groupId = "product-topic-consumer-group",
            concurrency = "1",
            batch = "true"
    )
    public void handle(@Payload List<String> products, Acknowledgment Acknowledgment) {

        System.out.println("ok");
        Acknowledgment.acknowledge();
    }
}
