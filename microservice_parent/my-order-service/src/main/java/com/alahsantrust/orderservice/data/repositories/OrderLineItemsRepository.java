package com.alahsantrust.orderservice.data.repositories;

import com.alahsantrust.orderservice.data.models.entities.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineItemsRepository extends JpaRepository<OrderLineItems, Long > {
}
