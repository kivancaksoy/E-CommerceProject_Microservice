package com.kivanc.ecommerce.productservice.util;

import com.kivanc.ecommerce.productservice.exception.BusinessRuleViolationException;

public class ProductImageBusinessRuleUtil {
    public static void checkProductImageCount(int imageCount) {
        if (imageCount >= 2) {
            throw new BusinessRuleViolationException("A product cannot have more than 2 images.");
        }
    }
}
