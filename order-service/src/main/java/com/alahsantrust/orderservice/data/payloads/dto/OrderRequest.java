package com.alahsantrust.orderservice.data.payloads.dto;

import com.alahsantrust.orderservice.data.models.entities.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private List<OrderLineItemsRequest> orderLineItemsRequestList;
}
