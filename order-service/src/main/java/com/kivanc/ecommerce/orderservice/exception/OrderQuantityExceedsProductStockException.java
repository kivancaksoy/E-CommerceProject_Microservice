package com.kivanc.ecommerce.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class OrderQuantityExceedsProductStockException extends RuntimeException {
    public OrderQuantityExceedsProductStockException(String message) {
        super(message);
    }
}
