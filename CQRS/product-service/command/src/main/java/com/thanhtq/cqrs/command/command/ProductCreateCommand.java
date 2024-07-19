package com.thanhtq.cqrs.command.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreateCommand {
    private String productId;
    private String name;
    private Integer quantity;
    private BigDecimal price;
}
