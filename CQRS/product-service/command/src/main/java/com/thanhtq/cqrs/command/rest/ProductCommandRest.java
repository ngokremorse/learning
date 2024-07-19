package com.thanhtq.cqrs.command.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thanhtq.cqrs.command.command.ProductCreateCommand;
import com.thanhtq.cqrs.command.model.ProductModel;
import com.thanhtq.cqrs.command.service.ProductService;
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

    private final ProductService productService;

    @PostMapping("/create")
    public String createProduct(@RequestBody ProductModel productModel) throws JsonProcessingException {

        ProductCreateCommand productCreateCommand = ProductCreateCommand
                .builder()
                .productId(UUID.randomUUID().toString())
                .name(productModel.getName())
                .price(productModel.getPrice())
                .quantity(productModel.getQuantity())
                .build();
        productService.on(productCreateCommand);
        return productCreateCommand.getProductId();
    }

}
