package com.amelia.codes.productservice.application.dto.service;

import com.amelia.codes.productservice.application.dto.ProductDto;
import com.amelia.codes.productservice.domain.model.Product;
import com.amelia.codes.productservice.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<ProductDto> findAll() {
        return repo.findAll().stream()
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getPrice()))
                .toList();
    }

    public ProductDto save(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());
        Product saved = repo.save(product);
        return new ProductDto(saved.getId(), saved.getName(), saved.getPrice());
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
