package com.alahsantrust.productservice.services;

import com.alahsantrust.productservice.data.models.entities.Product;
import com.alahsantrust.productservice.data.payloads.dto.ProductRequest;
import com.alahsantrust.productservice.data.payloads.dto.ProductResponse;
import com.alahsantrust.productservice.data.payloads.mapper.dto_to_entity.ProductDtoToProductEntity;
import com.alahsantrust.productservice.data.payloads.mapper.entity_to_dto.ProductEntityToProductResponse;
import com.alahsantrust.productservice.data.payloads.responses.messages.Message;
import com.alahsantrust.productservice.data.payloads.responses.messages.MessageResponse;
import com.alahsantrust.productservice.data.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductDtoToProductEntity productDtoToProductEntity;
    private final ProductEntityToProductResponse productEntityToProductResponse;
    @Override
    public MessageResponse createProduct(ProductRequest productRequest){

        try {
            var product =productDtoToProductEntity.apply(productRequest);
            var productSearch =productRepository.findAllByProductName(productRequest.getProductName().trim().toUpperCase()).orElse(null);
            if (productSearch==null){
                productRepository.save(product);
                log.info("product {} is saved" , product.getId());
                return new MessageResponse(Message.PRODUCT_SAVE_MESSAGE);
            }else {
                log.info("product id = {} and product name = {} , that is already existed." , productSearch.getId(), productSearch.getProductName());
                return new MessageResponse(Message.PRODUCT_ALREADY_EXISTED);
            }
        }catch (Exception e){
            return new MessageResponse(Message.PRODUCT_FAILED_MESSAGE);
        }

    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        var allProducts=  products.stream().map(
                productEntityToProductResponse::apply
        ).toList();
        return allProducts;
    }


    @Override
    public MessageResponse deleteProductByProductName(String productName){
        try {
            var productSearch =productRepository.findAllByProductName(productName.trim().toUpperCase()).orElse(null);
            if (productSearch !=null){
                productRepository.delete(productSearch);
                log.info("Product with name {} is deleted." , productName);
                return new MessageResponse(Message.PRODUCT_DELETE_SUCCESS_MESSAGE);
            }else {
                log.info("Product with name {} not found for deletion", productName);
                return new MessageResponse(productName +Message.PRODUCT_NOT_FOUND );
            }
        }catch (Exception e){
            return new MessageResponse(Message.PRODUCT_DELETE_FAILED_MESSAGE + e.getMessage());
        }
    }








//    public void createProduct(ProductRequest productRequest){
//
//        try {
//            Product product = Product.builder()
//                    .id(UUID.randomUUID().toString())
//                    .productName(productRequest.getProductName())
//                    .productDescription(productRequest.getProductDescription())
//                    .productPrice(productRequest.getProductPrice())
//                    .build();
//            productRepository.save(product);
//            log.info("product {} is saved" , product.getId());
//        }catch (Exception e){
//            throw new RuntimeException("Product can't saved because of "+e.getMessage());
//        }
//
//    }

//    public List<ProductResponse> getAllProducts() {
//        List<Product> products = productRepository.findAll();
//
//
//      var allProducts=  products.stream().map(
//               // this::mapToProductResponse // =this line == product -> mapToProductResponse(product)
//
//        ).toList();
//      return allProducts;
//    }

//    private ProductResponse mapToProductResponse(Product product) {
//       var productResponse =  ProductResponse.builder()
//                .id(product.getId())
//                .productName(product.getProductName())
//                .productPrice(product.getProductPrice())
//                .productDescription(product.getProductDescription())
//                .build();
//        return productResponse;
//    }
}
