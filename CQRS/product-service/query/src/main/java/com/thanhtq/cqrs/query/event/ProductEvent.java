package com.thanhtq.cqrs.query.event;

import com.thanhtq.cqrs.query.enums.EventType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductEvent {

    private EventType eventType;
    private String productId;
    private String name;
    private Integer quantity;
    private BigDecimal price;
}
