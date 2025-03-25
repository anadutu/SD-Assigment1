package com.ana.demo.service;

import com.ana.demo.model.Product;
import com.ana.demo.model.ProductCreateDTO;
import com.ana.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    // Get all products
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    // Add a new product
    public Product addProduct(ProductCreateDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());

        return productRepository.save(product);
    }

    // Update an existing product
    public Product updateProduct(UUID uuid, Product productDetails) {
        Product existingProduct = productRepository.findById(uuid)
                .orElseThrow(() -> new IllegalStateException("Product with UUID " + uuid + " not found"));

        // Update fields if provided
        if (productDetails.getName() != null) {
            existingProduct.setName(productDetails.getName());
        }
        if (productDetails.getPrice() != null) {
            existingProduct.setPrice(productDetails.getPrice());
        }
        if (productDetails.getDescription() != null) {
            existingProduct.setDescription(productDetails.getDescription());
        }

        return productRepository.save(existingProduct);
    }

    // Delete a product by UUID
    public void deleteProduct(UUID uuid) {
        if (!productRepository.existsById(uuid)) {
            throw new IllegalStateException("Product with UUID " + uuid + " not found");
        }
        productRepository.deleteById(uuid);
    }

    // Get a product by name
    public Product getProductByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new IllegalStateException("Product with name " + name + " not found"));
    }

    // Get a product by UUID
    public Product getProductById(UUID uuid) {
        return productRepository.findById(uuid)
                .orElseThrow(() -> new IllegalStateException("Product with UUID " + uuid + " not found"));
    }

    // Get products by price range
    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    // Get products whose name starts or ends with the given string
    public List<Product> getProductsByNameApproximate(String name) {
        return productRepository.findByNameApproximate(name);
    }
}