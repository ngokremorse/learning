package com.thanhtq.cqrs.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thanhtq.cqrs.command.ProductCreateCommand;
import com.thanhtq.cqrs.aggregate.ProductAggregate;
import com.thanhtq.cqrs.model.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product/")
public class ProductCommandRest {

    private final ProductAggregate productAggregate;

    @PostMapping("/create")
    public String createProduct(@RequestBody ProductModel productModel) throws JsonProcessingException {
        ProductCreateCommand productCreateCommand = ProductCreateCommand
                .builder()
                .productId(UUID.randomUUID().toString())
                .name(productModel.getName())
                .price(productModel.getPrice())
                .quantity(productModel.getQuantity())
                .build();
        productAggregate.on(productCreateCommand);
        return productCreateCommand.getProductId();
    }

}
