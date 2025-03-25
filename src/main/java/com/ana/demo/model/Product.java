package com.ana.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "products") // Specifies the table name in the database
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-generates the UUID
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price; // Price of the product

    @Column(name = "description", columnDefinition = "TEXT")
    private String description; // Optional description of the product

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts; // List of order-product associations

}