package com.ana.demo.repository;

import com.ana.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    // Find a user by email
    Optional<User> findByEmail(String email);

    // Find a user by email and role
    Optional<User> findByEmailAndRole(String email, String role);

    // Find users whose name starts or ends with the given string (JPQL Query)
    @Query("SELECT u FROM User u WHERE u.name LIKE ?1% OR u.name LIKE %?1")
    List<User> findByNameApproximate(String name);

    // Find users whose name starts with or ends with the given strings (Derived Query)
    List<User> findByNameStartingWithOrNameEndingWith(String start, String end);

    // Find all users with a specific role
    List<User> findByRole(String role);
}