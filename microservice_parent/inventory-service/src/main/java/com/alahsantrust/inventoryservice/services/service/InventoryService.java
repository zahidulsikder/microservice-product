package com.alahsantrust.inventoryservice.services.service;

import com.alahsantrust.inventoryservice.data.payloads.responses.InventoryResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface InventoryService {
    public List<InventoryResponse> isInStock(List<String> skuCode);
}
