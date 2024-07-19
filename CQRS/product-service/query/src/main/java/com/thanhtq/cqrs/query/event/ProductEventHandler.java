package com.thanhtq.cqrs.query.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thanhtq.cqrs.query.service.ProductProjectionService;
import com.thanhtq.cqrs.query.service.ProductService;
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
    private final ProductService productService;
    private final ProductProjectionService productProjectionService;

    @Override
    @KafkaListener(
            topics = "product-topic",
            groupId = "product-query-consumer-group",
            concurrency = "1",
            batch = "true"
    )
    public void handler(@Payload List<String> productEvents, Acknowledgment acknowledgment) throws JsonProcessingException {
        // prepare data
        ProductProjectionService.ProductAggregate productAggregate = productProjectionService.aggregate(productEvents);
        // Save to Read database, elastic search, redis, ....
        acknowledgment.acknowledge();
    }

    public void created() {

    }

    public void update() {

    }

    @Override
    public void handler(String productEvent, Acknowledgment acknowledgment) {

    }
}
