package com.amelia.codes.ProductService.infrastructure.repository;

import com.amelia.codes.ProductService.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
