package com.alahsantrust.productservice.data.repositories;

import com.alahsantrust.productservice.data.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
