package com.alahsantrust.productservice.controllers;

import com.alahsantrust.productservice.data.payloads.dto.ProductRequest;
import com.alahsantrust.productservice.data.payloads.dto.ProductResponse;
import com.alahsantrust.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    public final  ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void createdProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> productResponses(){
       return productService.getAllProducts();
    }

}
