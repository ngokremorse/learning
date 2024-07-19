package com.thanhtq.cqrs.command.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    private String name;
    private Integer quantity;
    private BigDecimal price;
}
