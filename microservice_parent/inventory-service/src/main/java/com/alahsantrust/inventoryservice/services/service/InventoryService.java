package com.alahsantrust.inventoryservice.services.service;

import org.springframework.stereotype.Component;

@Component
public interface InventoryService {
    public boolean isInStock(String skuCode);
}
