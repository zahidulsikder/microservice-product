package com.alahsantrust.productservice.services;

import com.alahsantrust.productservice.data.payloads.dto.ProductRequest;
import com.alahsantrust.productservice.data.payloads.dto.ProductResponse;
import com.alahsantrust.productservice.data.payloads.responses.messages.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ProductService {
    public MessageResponse createProduct(ProductRequest productRequest);
    public List<ProductResponse> getAllProducts();
    public MessageResponse deleteProductByProductName(String productName);
}
