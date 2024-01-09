package com.alahsantrust.productservice.data.payloads.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotNull(message = "Invalid productName : Product Name can't be null")
    @NotBlank(message = "Invalid productName : Product Name can't be blank")
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
}
