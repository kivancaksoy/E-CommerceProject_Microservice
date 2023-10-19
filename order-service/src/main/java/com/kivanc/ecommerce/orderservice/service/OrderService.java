package com.kivanc.ecommerce.orderservice.service;

import com.kivanc.ecommerce.orderservice.client.ProductServiceClient;
import com.kivanc.ecommerce.orderservice.dto.CreateOrderRequest;
import com.kivanc.ecommerce.orderservice.dto.OrderItemDto;
import com.kivanc.ecommerce.orderservice.dto.ProductDto;
import com.kivanc.ecommerce.orderservice.dto.converter.OrderConverter;
import com.kivanc.ecommerce.orderservice.exception.OrderQuantityExceedsProductStockException;
import com.kivanc.ecommerce.orderservice.model.Order;
import com.kivanc.ecommerce.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final ProductServiceClient productServiceClient;

    public OrderService(OrderRepository orderRepository, OrderConverter orderConverter, ProductServiceClient productServiceClient) {
        this.orderRepository = orderRepository;
        this.orderConverter = orderConverter;
        this.productServiceClient = productServiceClient;
    }

    public void createOrder(CreateOrderRequest createOrderRequest) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderItemDto orderItemDto : createOrderRequest.orderItems()) {
            ProductDto productDto = productServiceClient.getProductById(orderItemDto.id()).getBody();
            if (orderItemDto.quantity() > productDto.unitsInStock()) {
                throw new OrderQuantityExceedsProductStockException("The order quantity exceeds the available stock. Available stock for " + orderItemDto.id() + ": " + productDto.unitsInStock());
            }
            totalPrice = totalPrice.add(productDto.price().multiply(BigDecimal.valueOf(orderItemDto.quantity())));
        }
        productServiceClient.updateStock(createOrderRequest.orderItems());

        Order order = orderConverter.convertToOrder(createOrderRequest, totalPrice);
        orderRepository.save(order);
    }

}
