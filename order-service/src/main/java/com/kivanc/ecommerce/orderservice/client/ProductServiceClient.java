package com.kivanc.ecommerce.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "product-service", path = "/v1/product")
public interface ProductServiceClient {

    @GetMapping("/getpricebyid")
    ResponseEntity<BigDecimal> getProductPriceById(@RequestParam(name = "id") String id);

}
