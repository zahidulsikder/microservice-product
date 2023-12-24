package com.alahsantrust.productservice.services;

import com.alahsantrust.productservice.data.models.entities.Product;
import com.alahsantrust.productservice.data.payloads.dto.ProductRequest;
import com.alahsantrust.productservice.data.payloads.dto.ProductResponse;
import com.alahsantrust.productservice.data.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {


    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){

        try {
            Product product = Product.builder()
                    .id(UUID.randomUUID().toString())
                    .productName(productRequest.getProductName())
                    .productDescription(productRequest.getProductDescription())
                    .productPrice(productRequest.getProductPrice())
                    .build();
            productRepository.save(product);
            log.info("product {} is saved" , product.getId());
        }catch (Exception e){
            throw new RuntimeException("Product can't saved because of "+e.getMessage());
        }

    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
      var allProducts=  products.stream().map(
                this::mapToProductResponse // =this line == product -> mapToProductResponse(product)
        ).toList();
      return allProducts;
    }

    private ProductResponse mapToProductResponse(Product product) {
       var productResponse =  ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productDescription(product.getProductDescription())
                .build();
        return productResponse;
    }
}
