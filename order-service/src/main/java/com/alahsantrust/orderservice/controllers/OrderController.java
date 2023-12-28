package com.alahsantrust.orderservice.controllers;

import com.alahsantrust.orderservice.data.models.entities.Order;
import com.alahsantrust.orderservice.data.payloads.dto.OrderRequest;
import com.alahsantrust.orderservice.data.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "order-placed";
    }

    @GetMapping
    public List<Order> orders(){
        return orderService.orders();
    }

}
