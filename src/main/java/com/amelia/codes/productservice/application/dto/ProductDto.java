package com.amelia.codes.productservice.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDto(
        UUID id,

        @NotBlank(message = "Name is required")
        String name,

        @DecimalMin(value = "0.01", message = "Price must be positive")
        BigDecimal price
) {}