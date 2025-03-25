package com.ana.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data // Lombok annotation for getters, setters, toString, equals, and hashCode
@Table(name = "users") // Define the table name in the database
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-generate UUID
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role; // e.g., "admin", "customer"
}