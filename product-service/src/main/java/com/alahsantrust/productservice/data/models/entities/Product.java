package com.alahsantrust.productservice.data.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Documented;
import java.math.BigDecimal;
@Entity
@Table(name = "mic_products")
@Data
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class Product {
    @Id
    private  String id;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
}
