package com.alahsantrust.orderservice.services;

import com.alahsantrust.orderservice.data.models.entities.Order;
import com.alahsantrust.orderservice.data.models.entities.OrderLineItems;
import com.alahsantrust.orderservice.data.payloads.dto.OrderLineItemsRequest;
import com.alahsantrust.orderservice.data.payloads.dto.OrderRequest;
import com.alahsantrust.orderservice.data.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

    List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsRequestList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);


        //calling inventory service and place an order if product is available in inventory



      Boolean result =  webClient.get().uri("http://localhost:8082/api/inventory")
                        .retrieve()
                        .bodyToMono(Boolean.class)
                                .block();


      if (result){

      }else {
          throw new IllegalArgumentException("Product is not in stock, please try again later.");
      }







        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsRequest orderLineItemsRequest) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setQuantity(orderLineItemsRequest.getQuantity());
        orderLineItems.setPrice(orderLineItemsRequest.getPrice());
        orderLineItems.setSkuCode(orderLineItemsRequest.getSkuCode());
        return orderLineItems;
    }


    public List<Order> orders (){
        return orderRepository.findAll();
    }

}
