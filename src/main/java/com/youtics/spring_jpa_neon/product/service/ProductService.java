package com.youtics.spring_jpa_neon.product.service;

import com.youtics.spring_jpa_neon.product.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    // Obtener todos los productos
    List<Product> getAllProducts();

    // Obtener un producto por ID
    Optional<Product> getProductById(Long id);

    // Crear un producto
    Product createProduct(Product product);

    // Actualizar un producto existente
    Product updateProduct(Long id, Product product);

    // Eliminar un producto
    void deleteProduct(Long id);

}
