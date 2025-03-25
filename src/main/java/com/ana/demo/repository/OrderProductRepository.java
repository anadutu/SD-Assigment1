package com.ana.demo.repository;

import com.ana.demo.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {

    // Find all order products for a specific order
    List<OrderProduct> findByOrderId(UUID orderId);

    // Find all order products for a specific product (using product name)
    List<OrderProduct> findByProductName(String productName);

    // JPQL Query: Find all order products for a specific product (using product name)
    @Query("SELECT op FROM OrderProduct op WHERE op.product.name = ?1")
    List<OrderProduct> findOrderProductsByProductName(String productName);
}