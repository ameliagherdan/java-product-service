package com.amelia.codes.ProductService.infrastructure.adapter;

import com.amelia.codes.ProductService.domain.model.Product;
import com.amelia.codes.ProductService.domain.port.ProductRepositoryPort;
import com.amelia.codes.ProductService.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductRepository jpaRepo;

    public ProductRepositoryAdapter(ProductRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public List<Product> findAll() {
        return jpaRepo.findAll();
    }

    @Override
    public Product save(Product product) {
        return jpaRepo.save(product);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepo.deleteById(id);
    }
}
