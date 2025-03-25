package com.ana.demo.repository;

import com.ana.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    // Find all orders by a specific user (using email)
    List<Order> findByUserEmail(String userEmail);

    // JPA Derived Query: Find an order by its status
    List<Order> findByStatus(String status);

    // JPQL Query: Find all orders for a specific user (using email)
    @Query("SELECT o FROM Order o WHERE o.user.email = ?1")
    List<Order> findOrdersByUserEmail(String userEmail);
}