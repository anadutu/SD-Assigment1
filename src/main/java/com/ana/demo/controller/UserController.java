package com.ana.demo.controller;

import com.ana.demo.model.CustomException;
import com.ana.demo.model.User;
import com.ana.demo.model.UserCreateDTO;
import com.ana.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin // Allows cross-origin requests (e.g., from a frontend application)
@RequestMapping("/users") // Base URL for all user-related endpoints
public class UserController {
    private final UserService userService;

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    // Get a user by UUID
    @GetMapping("/{uuid}")
    public ResponseEntity<User> getUserById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(userService.getUserById(uuid));
    }

    // Get a user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    // Add a new user
    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody UserCreateDTO userDTO) throws CustomException {
        return ResponseEntity.ok(userService.addUser(userDTO));
    }

    // Update an existing user
    @PutMapping("/{uuid}")
    public ResponseEntity<User> updateUser(@PathVariable UUID uuid, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(uuid, user));
    }

    // Delete a user by UUID
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID uuid) {
        userService.deleteUser(uuid);
        return ResponseEntity.noContent().build();
    }

    // Get users by role
    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role) {
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }
}