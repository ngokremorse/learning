package com.thanhtq.cqrs.command.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanhtq.cqrs.command.command.ProductCreateCommand;
import com.thanhtq.cqrs.command.command.ProductDeleteCommand;
import com.thanhtq.cqrs.command.command.ProductUpdateCommand;
import com.thanhtq.cqrs.command.enums.EventType;
import com.thanhtq.cqrs.command.event.ProductEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;


    public void on(ProductCreateCommand productCreateCommand) throws JsonProcessingException {
        // invalidate
        ProductEvent productEvent = ProductEvent
                .builder()
                .eventType(EventType.CREATED)
                .build();
        BeanUtils.copyProperties(productCreateCommand, productEvent);
        kafkaTemplate.send("product-topic", productEvent.getProductId(), objectMapper.writeValueAsString(productEvent));
    }

    public void on(ProductUpdateCommand productUpdateCommand) throws JsonProcessingException {
        ProductEvent productEvent = ProductEvent
                .builder()
                .eventType(EventType.UPDATED)
                .build();
        BeanUtils.copyProperties(productUpdateCommand, productEvent);
        kafkaTemplate.send("product-topic", productUpdateCommand.getProductId(), objectMapper.writeValueAsString(productUpdateCommand));
    }

    public void on(ProductDeleteCommand productDeleteCommand) throws JsonProcessingException {
        ProductEvent productEvent = ProductEvent
                .builder()
                .eventType(EventType.DELETED)
                .build();
        BeanUtils.copyProperties(productDeleteCommand, productEvent);
        kafkaTemplate.send("product-topic", productEvent.getProductId(), objectMapper.writeValueAsString(productEvent));
    }

}
