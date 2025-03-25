package com.ana.demo.service;

import com.ana.demo.model.User;
import com.ana.demo.model.UserCreateDTO;
import com.ana.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // Get all users
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Add a new user
    public User addUser(UserCreateDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());

        return userRepository.save(user);
    }

    // Update an existing user
    public User updateUser(UUID uuid, User user) {
        User existingUser = userRepository.findById(uuid)
                .orElseThrow(() -> new IllegalStateException("User with UUID " + uuid + " not found"));

        // Update fields if provided
        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }
        if (user.getRole() != null) {
            existingUser.setRole(user.getRole());
        }

        return userRepository.save(existingUser);
    }

    // Delete a user by UUID
    public void deleteUser(UUID uuid) {
        if (!userRepository.existsById(uuid)) {
            throw new IllegalStateException("User with UUID " + uuid + " not found");
        }
        userRepository.deleteById(uuid);
    }

    // Get a user by email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User with email " + email + " not found"));
    }

    // Get a user by UUID
    public User getUserById(UUID uuid) {
        return userRepository.findById(uuid)
                .orElseThrow(() -> new IllegalStateException("User with UUID " + uuid + " not found"));
    }

    // Get users by role
    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }
}