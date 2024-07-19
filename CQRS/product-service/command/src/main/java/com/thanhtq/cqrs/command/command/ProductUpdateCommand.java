package com.thanhtq.cqrs.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductUpdateCommand {
    private String productId;
    private String name;
    private Integer quantity;
    private BigDecimal price;
}
