package com.alahsantrust.productservice.data.payloads.mapper.entity_to_dto;

import com.alahsantrust.productservice.data.models.entities.Product;
import com.alahsantrust.productservice.data.payloads.dto.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductEntityToProductResponse implements Function<Product, ProductResponse> {
    @Override
    public ProductResponse apply(Product product) {
        var productResponse =  ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productDescription(product.getProductDescription())
                .build();
        return productResponse;
    }
}
