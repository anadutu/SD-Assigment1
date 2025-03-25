package com.ana.demo.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductCreateDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private Double price;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description; // Optional field
}