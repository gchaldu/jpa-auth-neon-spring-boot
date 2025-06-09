package com.youtics.spring_jpa_neon.product.service;

import com.youtics.spring_jpa_neon.product.dto.ProductDTO;
import com.youtics.spring_jpa_neon.product.model.Product;
import com.youtics.spring_jpa_neon.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, Product product) {
        return repository.findById(id)
                .map(existing -> {
                    // Actualizamos solo campos necesarios, no todo el objeto entero
                    existing.setName(product.getName());
                    existing.setPrice(product.getPrice());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id));
    }


    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id));
        repository.delete(product);
    }

}
