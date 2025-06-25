package com.amelia.codes.ProductService.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ProductCreateDto(
        @NotBlank(message = "Name is required")
        String name,

        @DecimalMin(value = "0.01", message = "Price must be positive")
        BigDecimal price
) {}