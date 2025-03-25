package com.ana.demo.controller;

import com.ana.demo.model.Product;
import com.ana.demo.model.ProductCreateDTO;
import com.ana.demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin // Allows cross-origin requests (e.g., from a frontend application)
@RequestMapping("/products") // Base URL for all product-related endpoints
public class ProductController {
    private final ProductService productService;

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    // Get a product by UUID
    @GetMapping("/{uuid}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(productService.getProductById(uuid));
    }

    // Get a product by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    // Add a new product
    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductCreateDTO productDTO) {
        return ResponseEntity.ok(productService.addProduct(productDTO));
    }

    // Update an existing product
    @PutMapping("/{uuid}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID uuid, @RequestBody Product productDetails) {
        return ResponseEntity.ok(productService.updateProduct(uuid, productDetails));
    }

    // Delete a product by UUID
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID uuid) {
        productService.deleteProduct(uuid);
        return ResponseEntity.noContent().build();
    }

    // Get products by price range
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        return ResponseEntity.ok(productService.getProductsByPriceRange(minPrice, maxPrice));
    }

    // Get products whose name starts or ends with the given string
    @GetMapping("/name-approximate/{name}")
    public ResponseEntity<List<Product>> getProductsByNameApproximate(@PathVariable String name) {
        return ResponseEntity.ok(productService.getProductsByNameApproximate(name));
    }
}