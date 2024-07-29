package com.thanhtq.cqrs.query.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanhtq.cqrs.query.enums.EventType;
import com.thanhtq.cqrs.query.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductProjectionEventHandler implements EventHandler {
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void handler(List<String> productEvents, Acknowledgment acknowledgment) throws JsonProcessingException {
        List<ProductEvent> productEventCreated = new ArrayList<>();
        List<ProductEvent> productEventUpdated = new ArrayList<>();
        List<ProductEvent> productEventDeleted = new ArrayList<>();
        for (String productEventString : productEvents) {
            ProductEvent productEvent = objectMapper.readValue(productEventString, new TypeReference<>() {});
            if (productEvent.getEventType().equals(EventType.CREATED)) {
                productEventCreated.add(productEvent);
            } else if (productEvent.getEventType().equals(EventType.UPDATED)) {
                // Nếu tồn tại trong delete thì loại bỏ khỏi danh sách
                Optional<ProductEvent> exits = productEventUpdated.stream().filter(item -> item.getProductId().equals(productEvent.getProductId())).findFirst();
                if(exits.isPresent()) {
                    productEventUpdated.remove(exits.get());
                    continue;
                }
                // xử lý update
                ProductEvent productEventInList = productEventUpdated.stream().filter(item -> item.getProductId().equals(productEvent.getProductId())).findFirst().orElse(null);
                if (productEventInList == null) {
                    productEventUpdated.add(productEvent);
                } else {
                    BeanUtils.copyProperties(productEvent, productEventInList);
                }
            } else {
                productEventDeleted.add(productEvent);
            }
        }
    }

    @Override
    public void handler(String productEvent, Acknowledgment acknowledgment) {

    }

}
