package com.kivanc.ecommerce.productservice.repository;

import com.kivanc.ecommerce.productservice.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, String> {
}
