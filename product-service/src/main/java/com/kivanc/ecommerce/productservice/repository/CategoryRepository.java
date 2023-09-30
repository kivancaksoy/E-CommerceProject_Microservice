package com.kivanc.ecommerce.productservice.repository;

import com.kivanc.ecommerce.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
