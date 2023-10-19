package com.kivanc.ecommerce.orderservice.client;

import com.kivanc.ecommerce.orderservice.dto.CreateOrderRequest;
import com.kivanc.ecommerce.orderservice.dto.OrderItemDto;
import com.kivanc.ecommerce.orderservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "product-service", path = "/v1/product")
public interface ProductServiceClient {

    @GetMapping("/getProductPriceById")
    ResponseEntity<BigDecimal> getProductPriceById(@RequestParam(name = "id") String id);

    @PutMapping("/updateStock")
    ResponseEntity<Void> updateStock(@RequestBody List<OrderItemDto> orderItemDtoList);

    @GetMapping("/getById")
    ResponseEntity<ProductDto> getProductById(@RequestParam(name = "id") String id);

}
