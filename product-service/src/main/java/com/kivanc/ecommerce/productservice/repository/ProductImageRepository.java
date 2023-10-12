package com.kivanc.ecommerce.productservice.repository;

import com.kivanc.ecommerce.productservice.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, String> {
    List<ProductImage> findByProductId(String productId);
}
