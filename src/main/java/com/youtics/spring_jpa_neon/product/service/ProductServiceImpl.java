package com.youtics.spring_jpa_neon.product.service;

import com.youtics.spring_jpa_neon.product.model.Product;
import com.youtics.spring_jpa_neon.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService{

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }
}
