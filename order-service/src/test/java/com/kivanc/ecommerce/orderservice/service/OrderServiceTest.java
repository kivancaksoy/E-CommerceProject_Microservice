package com.kivanc.ecommerce.orderservice.service;

import com.kivanc.ecommerce.orderservice.client.ProductServiceClient;
import com.kivanc.ecommerce.orderservice.dto.CreateOrderRequest;
import com.kivanc.ecommerce.orderservice.dto.OrderItemDto;
import com.kivanc.ecommerce.orderservice.dto.ProductDto;
import com.kivanc.ecommerce.orderservice.dto.converter.OrderConverter;
import com.kivanc.ecommerce.orderservice.model.Order;
import com.kivanc.ecommerce.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

class OrderServiceTest {
    private OrderRepository orderRepository;
    private OrderConverter orderConverter;
    private ProductServiceClient productServiceClient;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderConverter = mock(OrderConverter.class);
        productServiceClient = mock(ProductServiceClient.class);
        orderService = new OrderService(orderRepository, orderConverter, productServiceClient);
    }

    @Test
    void testCreateOrderSuccess() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(
                List.of(new OrderItemDto("product-1", 2))
        );
        ProductDto productDto = new ProductDto(BigDecimal.valueOf(10), 5);
        Order order = new Order();

        when(productServiceClient.getProductById("product-1")).thenReturn(ResponseEntity.ok(productDto));
        when(orderConverter.convertToOrder(createOrderRequest, BigDecimal.valueOf(20))).thenReturn(order);

        orderService.createOrder(createOrderRequest);
        verify(productServiceClient).getProductById("product-1");
        verify(productServiceClient).updateStock(createOrderRequest.orderItems());
        verify(orderConverter).convertToOrder(createOrderRequest, BigDecimal.valueOf(20));
        verify(orderRepository).save(order);
    }
}