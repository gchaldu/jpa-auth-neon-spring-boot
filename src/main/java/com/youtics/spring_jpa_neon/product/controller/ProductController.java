package com.youtics.spring_jpa_neon.product.controller;

import com.youtics.spring_jpa_neon.product.dto.ProductDTO;
import com.youtics.spring_jpa_neon.product.model.Product;
import com.youtics.spring_jpa_neon.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<Product> products = service.getAllProducts();
        if(!products.isEmpty()){
            return ResponseEntity.ok(products.stream().map(ProductDTO::new).toList());
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return service.createProduct(product);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> createProduct2(@RequestBody Product product){
        Product savedProduct = service.createProduct(product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedProduct);
    }

    @PostMapping("/add2")
    public ResponseEntity<Product> createProduct3(@RequestBody Product product) {
        Product savedProduct = service.createProduct(product);
        URI location = URI.create("/products/add" + savedProduct.getId());
        return ResponseEntity
                .created(location)
                .body(savedProduct);
    }

    @PostMapping("/product")
    public ResponseEntity<?> crearProducto(@Valid @RequestBody ProductDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errores);
        }

        // 1. Convertir DTO a entidad (esto puede hacerse con un mapper si preferís)
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());

        // 2. Guardar usando el servicio
        Product savedProduct = service.createProduct(product);

        // 3. Devolver una respuesta
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedProduct);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = service.getProductById(id);
        if (product.isPresent()) {
            service.deleteProduct(id);
            // 204 No Content
            return ResponseEntity.noContent().build();
        } else {
            // 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct2(@PathVariable Long id) {
        Optional<Product> product = service.getProductById(id);
        if (product.isPresent()) {
            service.deleteProduct(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Producto eliminado correctamente");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Producto no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> existing = service.getProductById(id);
        Map<String, Object> response = new HashMap<>();

        if (existing.isPresent()) {
            Product updated = service.updateProduct(id, product);
            response.put("message", "Producto actualizado correctamente");
            response.put("product", updated); // se serializa como JSON
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Producto no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }



















}
