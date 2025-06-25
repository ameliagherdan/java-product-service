package com.amelia.codes.ProductService.infrastructure.repository;

import com.amelia.codes.ProductService.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
