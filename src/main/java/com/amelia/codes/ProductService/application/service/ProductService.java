package com.amelia.codes.ProductService.application.service;

import com.amelia.codes.ProductService.application.dto.ProductCreateDto;
import com.amelia.codes.ProductService.application.dto.ProductDto;
import com.amelia.codes.ProductService.domain.model.Product;
import com.amelia.codes.ProductService.domain.port.ProductRepositoryPort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepositoryPort repo;

    public ProductService(ProductRepositoryPort repo) {
        this.repo = repo;
    }

    public List<ProductDto> findAll() {
        logger.info("Fetching all products...");
        return repo.findAll().stream()
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getPrice()))
                .toList();
    }

    @Transactional
    public ProductDto save(ProductCreateDto dto) {
        logger.info("Saving product: {}", dto);
        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());
        Product saved = repo.save(product);
        logger.debug("Saved product with ID: {}", saved.getId());

        return new ProductDto(saved.getId(), saved.getName(), saved.getPrice());
    }

    @Transactional
    public void delete(UUID id) {
        logger.warn("Deleting product with ID: {}", id);
        repo.deleteById(id);
    }
}