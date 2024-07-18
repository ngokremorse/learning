package com.thanhtq.cqrs.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateCommand {
    private String productId;
    private String name;
    private Integer quantity;
    private BigDecimal price;
}
