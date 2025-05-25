package com.youtics.spring_jpa_neon.product.service;

import com.youtics.spring_jpa_neon.product.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    Product createProduct(Product product);
}
