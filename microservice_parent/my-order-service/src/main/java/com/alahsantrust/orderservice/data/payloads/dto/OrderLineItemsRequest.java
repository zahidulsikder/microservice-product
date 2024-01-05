package com.alahsantrust.orderservice.data.payloads.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsRequest {
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
