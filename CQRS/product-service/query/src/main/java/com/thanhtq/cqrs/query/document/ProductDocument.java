package com.thanhtq.cqrs.query.document;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Dynamic;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.ZonedDateTime;

@Data
@Document(indexName = "product", dynamic = Dynamic.TRUE)
public class ProductDocument {

    @Id
    private String productId;
    private String name;
    @Field(
            type = FieldType.Long,
            name = "quantity"
    )
    private BigInteger quantity;
    @Field(
            type = FieldType.Double,
            name = "price"
    )
    private BigDecimal price;

    @Field(
            type = FieldType.Long,
            name = "created"
    )
    private ZonedDateTime created;

    @Field(
            type = FieldType.Long,
            name = "modified"
    )
    private ZonedDateTime modified;
}
