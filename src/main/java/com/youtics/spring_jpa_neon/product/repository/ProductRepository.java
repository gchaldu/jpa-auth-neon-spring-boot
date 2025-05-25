package com.youtics.spring_jpa_neon.product.repository;

import com.youtics.spring_jpa_neon.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
