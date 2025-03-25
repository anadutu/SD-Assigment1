package com.ana.demo.repository;

import com.ana.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    // Find a product by name
    Optional<Product> findByName(String name);

    // Find products by price range (JPA Derived Query)
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    // Find products whose name starts or ends with the given string (JPQL Query)
    @Query("SELECT p FROM Product p WHERE p.name LIKE ?1% OR p.name LIKE %?1")
    List<Product> findByNameApproximate(String name);

    // Find products whose name starts with one string or ends with another (Derived Query)
    List<Product> findByNameStartingWithOrNameEndingWith(String start, String end);
}