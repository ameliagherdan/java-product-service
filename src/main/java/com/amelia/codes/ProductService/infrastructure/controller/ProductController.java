package com.amelia.codes.ProductService.infrastructure.controller;
import com.amelia.codes.ProductService.application.dto.ProductDto;
import com.amelia.codes.ProductService.application.dto.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAll() {
        return productService.findAll();
    }

    @PostMapping
    public ProductDto create(@RequestBody @Valid ProductDto dto) {
        return productService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
