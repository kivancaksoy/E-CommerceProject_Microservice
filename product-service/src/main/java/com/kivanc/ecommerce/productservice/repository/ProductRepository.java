package com.kivanc.ecommerce.productservice.repository;

import com.kivanc.ecommerce.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
