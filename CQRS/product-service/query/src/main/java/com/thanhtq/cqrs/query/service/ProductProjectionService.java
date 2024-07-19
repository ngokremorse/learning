package com.thanhtq.cqrs.query.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanhtq.cqrs.query.enums.EventType;
import com.thanhtq.cqrs.query.event.ProductEvent;
import com.thanhtq.cqrs.query.repository.ProductRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductProjectionService {
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    public ProductAggregate aggregate(List<String> productEvents) throws JsonProcessingException {
        ProductAggregate productAggregate = new ProductAggregate();
        for (String productEventString : productEvents) {
            ProductEvent productEvent = objectMapper.readValue(productEventString, new TypeReference<>() {});
            if (productEvent.getEventType().equals(EventType.CREATED)) {
                productAggregate.getProductCreated().add(productEvent);
            } else if (productEvent.getEventType().equals(EventType.UPDATED)) {
                // Nếu tồn tại trong delete thì loại bỏ khỏi danh sách
                Optional<ProductEvent> exits = productAggregate.getProductDeleted().stream().filter(item -> item.getProductId().equals(productEvent.getProductId())).findFirst();
                if(exits.isPresent()) {
                    productAggregate.getProductUpdated().remove(exits.get());
                    continue;
                }
                // xử lý update
                ProductEvent productEventInList = productAggregate.getProductUpdated().stream().filter(item -> item.getProductId().equals(productEvent.getProductId())).findFirst().orElse(null);
                if (productEventInList == null) {
                    productAggregate.getProductUpdated().add(productEvent);
                } else {
                    BeanUtils.copyProperties(productEvent, productEventInList);
                }
            } else {
                productAggregate.getProductDeleted().add(productEvent);
            }
        }
        return productAggregate;
    }


    @Data
    public static class ProductAggregate {
        private List<ProductEvent> productCreated = new ArrayList<>();
        private List<ProductEvent> productUpdated = new ArrayList<>();
        private List<ProductEvent> productDeleted = new ArrayList<>();
    }
}
