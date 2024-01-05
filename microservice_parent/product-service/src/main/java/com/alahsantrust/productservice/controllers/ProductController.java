package com.alahsantrust.productservice.controllers;

import com.alahsantrust.productservice.data.payloads.dto.ProductRequest;
import com.alahsantrust.productservice.data.payloads.dto.ProductResponse;
import com.alahsantrust.productservice.data.payloads.responses.messages.MessageResponse;
import com.alahsantrust.productservice.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    public final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private MessageResponse createdProduct(@RequestBody @Valid ProductRequest productRequest){
       MessageResponse messageResponse = productService.createProduct(productRequest);
        return messageResponse;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> productResponses(){
       return productService.getAllProducts();
    }

    @DeleteMapping
    public ResponseEntity<MessageResponse> deleteProductByProductName(@RequestParam String productName) {
        MessageResponse response = productService.deleteProductByProductName(productName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
