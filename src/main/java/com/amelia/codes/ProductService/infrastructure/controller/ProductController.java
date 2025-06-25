package com.amelia.codes.ProductService.infrastructure.controller;
import com.amelia.codes.ProductService.application.dto.ProductCreateDto;
import com.amelia.codes.ProductService.application.dto.ProductDto;
import com.amelia.codes.ProductService.application.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
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
    public ProductDto create(@RequestBody @Valid ProductCreateDto dto) {
        return productService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        productService.delete(id);
    }
}
