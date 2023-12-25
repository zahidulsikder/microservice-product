package com.alahsantrust.productservice.data.payloads.mapper.dto_to_entity;

import com.alahsantrust.productservice.data.models.entities.Product;
import com.alahsantrust.productservice.data.payloads.dto.ProductRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Function;
@Component
public class ProductDtoToProductEntity implements Function<ProductRequest, Product> {
    @Override
    public Product apply(ProductRequest productRequest) {
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setProductName(productRequest.getProductName().trim().toUpperCase());
        product.setProductDescription(productRequest.getProductDescription());
        product.setProductPrice(productRequest.getProductPrice());
        return product;
    }


}
