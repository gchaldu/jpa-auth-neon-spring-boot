package com.youtics.spring_jpa_neon.product.controller;

import com.youtics.spring_jpa_neon.product.model.Product;
import com.youtics.spring_jpa_neon.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return service.createProduct(product);
    }















}
