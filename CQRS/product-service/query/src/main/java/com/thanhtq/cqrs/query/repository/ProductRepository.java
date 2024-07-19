package com.thanhtq.cqrs.query.repository;

import com.thanhtq.cqrs.query.document.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<ProductDocument, String> {
}
