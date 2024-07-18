package com.thanhtq.cqrs.event;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreatedEvent {
    private String productId;
    private String name;
    private Integer quantity;
    private BigDecimal price;
}
