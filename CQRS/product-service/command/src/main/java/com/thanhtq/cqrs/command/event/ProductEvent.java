package com.thanhtq.cqrs.command.event;

import com.thanhtq.cqrs.command.enums.EventType;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
