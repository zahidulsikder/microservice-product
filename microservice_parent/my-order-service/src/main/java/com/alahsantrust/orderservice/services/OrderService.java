package com.alahsantrust.orderservice.services;

import com.alahsantrust.orderservice.data.models.entities.Order;
import com.alahsantrust.orderservice.data.models.entities.OrderLineItems;
import com.alahsantrust.orderservice.data.payloads.dto.OrderLineItemsRequest;
import com.alahsantrust.orderservice.data.payloads.dto.OrderRequest;
import com.alahsantrust.orderservice.data.payloads.responses.InventoryResponse;
import com.alahsantrust.orderservice.data.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsRequestList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);


        //calling inventory service and place an order if product is available in inventory


        List<String> skuCodes = order.getOrderLineItemsList().stream().map(
                OrderLineItems::getSkuCode
        ).toList();


        InventoryResponse[] inventoryResponseArray = webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();


       boolean checkProductStock= Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);

        if (checkProductStock) {
            log.info("New order created Successfully"+ inventoryResponseArray);
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later.");
        }


    }

    private OrderLineItems mapToDto(OrderLineItemsRequest orderLineItemsRequest) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setQuantity(orderLineItemsRequest.getQuantity());
        orderLineItems.setPrice(orderLineItemsRequest.getPrice());
        orderLineItems.setSkuCode(orderLineItemsRequest.getSkuCode());
        return orderLineItems;
    }


    public List<Order> orders() {
        return orderRepository.findAll();
    }

}
