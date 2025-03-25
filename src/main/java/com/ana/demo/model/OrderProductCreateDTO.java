package com.ana.demo.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class OrderProductCreateDTO {

    @NotBlank(message = "Product name is required")
    private String productName; // Name of the product being ordered

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private Double price; // Price of the product at the time of ordering
}