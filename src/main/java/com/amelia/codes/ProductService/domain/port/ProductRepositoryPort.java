package com.amelia.codes.ProductService.domain.port;

import com.amelia.codes.ProductService.domain.model.Product;
import java.util.List;
import java.util.UUID;

public interface ProductRepositoryPort {
    List<Product> findAll();
    Product save(Product product);
    void deleteById(UUID id);
}
