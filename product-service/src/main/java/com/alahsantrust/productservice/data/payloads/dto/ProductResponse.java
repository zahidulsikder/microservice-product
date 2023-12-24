package com.alahsantrust.productservice.data.payloads.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private  String id;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
}
