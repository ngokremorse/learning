package com.thanhtq.cqrs.aggregate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanhtq.cqrs.command.ProductCreateCommand;
import com.thanhtq.cqrs.command.ProductDeleteCommand;
import com.thanhtq.cqrs.command.ProductUpdateCommand;
import com.thanhtq.cqrs.event.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductAggregate {
    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void on(ProductCreateCommand productCreateCommand) throws JsonProcessingException {
        // invalidate
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(productCreateCommand, productCreatedEvent);
        kafkaTemplate.send("product-topic", objectMapper.writeValueAsString(productCreatedEvent));
    }

    public void on(ProductUpdateCommand productUpdateCommand) {

    }

    public void on(ProductDeleteCommand productDeleteCommand) {

    }


}
