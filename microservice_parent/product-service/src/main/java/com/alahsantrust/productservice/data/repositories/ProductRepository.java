package com.alahsantrust.productservice.data.repositories;

import com.alahsantrust.productservice.data.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findAllByProductName(String productName);
}
