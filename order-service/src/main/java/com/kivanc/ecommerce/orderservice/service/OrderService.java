package com.kivanc.ecommerce.orderservice.service;

import com.kivanc.ecommerce.orderservice.dto.CreateOrderRequest;
import com.kivanc.ecommerce.orderservice.dto.converter.OrderConverter;
import com.kivanc.ecommerce.orderservice.model.Order;
import com.kivanc.ecommerce.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    public OrderService(OrderRepository orderRepository, OrderConverter orderConverter) {
        this.orderRepository = orderRepository;
        this.orderConverter = orderConverter;
    }

    public void createOrder(CreateOrderRequest createOrderRequest) {
        //TODO get precises for items from product-service with feign client.
        Order order = orderConverter.convertToOrder(createOrderRequest, BigDecimal.valueOf(120));
        orderRepository.save(order);
    }
}
