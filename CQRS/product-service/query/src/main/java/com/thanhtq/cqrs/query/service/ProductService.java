package com.thanhtq.cqrs.query.service;

import com.thanhtq.cqrs.query.document.ProductDocument;
import com.thanhtq.cqrs.query.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void indexProduct(ProductDocument productDocument) {
        productRepository.save(productDocument);
    }

    void indexProducts(List<ProductDocument> productDocumentList) {
        productRepository.saveAll(productDocumentList);
    }


}
