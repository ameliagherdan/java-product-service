package com.amelia.codes.productservice.infrastructure.repository;

import com.amelia.codes.productservice.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
