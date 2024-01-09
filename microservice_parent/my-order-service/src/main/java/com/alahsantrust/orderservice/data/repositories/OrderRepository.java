package com.alahsantrust.orderservice.data.repositories;

import com.alahsantrust.orderservice.data.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
