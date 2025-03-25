package com.ana.demo.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class OrderCreateDTO {

    @NotBlank(message = "User email is required")
    private String userEmail; // Email of the user placing the order

    @NotNull(message = "Total price is required")
    @DecimalMin(value = "0.01", message = "Total price must be greater than 0")
    private Double totalPrice;

    @NotBlank(message = "Status is required")
    private String status; // e.g., "PENDING", "SHIPPED", "DELIVERED"

    @NotEmpty(message = "Order items are required")
    private List<OrderProductCreateDTO> orderProducts; // List of products in the order
}