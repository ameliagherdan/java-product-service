package com.amelia.codes.ProductService.application.service;

import com.amelia.codes.ProductService.application.dto.ProductCreateDto;
import com.amelia.codes.ProductService.application.dto.ProductDto;
import com.amelia.codes.ProductService.domain.model.Product;
import com.amelia.codes.ProductService.domain.port.ProductRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest {

    private ProductRepositoryPort repo;
    private ProductService service;

    @BeforeEach
    void setUp() {
        repo = mock(ProductRepositoryPort.class);
        service = new ProductService(repo);
    }

    @Test
    void shouldReturnAllProducts() {
        // given
        UUID id = UUID.randomUUID();
        Product product = new Product();
        product.setId(id);
        product.setName("Test");
        product.setPrice(new BigDecimal("9.99"));

        when(repo.findAll()).thenReturn(List.of(product));

        // when
        List<ProductDto> result = service.findAll();

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).name()).isEqualTo("Test");
    }

    @Test
    void shouldSaveProductAndReturnDto() {
        // given
        ProductCreateDto dto = new ProductCreateDto("New", new BigDecimal("12.50"));
        Product savedProduct = new Product();
        savedProduct.setId(UUID.randomUUID());
        savedProduct.setName(dto.name());
        savedProduct.setPrice(dto.price());

        when(repo.save(any(Product.class))).thenReturn(savedProduct);

        // when
        ProductDto result = service.save(dto);

        // then
        assertThat(result.name()).isEqualTo("New");
        assertThat(result.price()).isEqualByComparingTo("12.50");
        verify(repo).save(any(Product.class));
    }

    @Test
    void shouldDeleteProductById() {
        // given
        UUID id = UUID.randomUUID();

        // when
        service.delete(id);

        // then
        verify(repo).deleteById(id);
    }

    @Test
    void shouldReturnEmptyListWhenNoProductsExist() {
        // given
        when(repo.findAll()).thenReturn(List.of());

        // when
        List<ProductDto> result = service.findAll();

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void shouldSaveProductWithCorrectValues() {
        // given
        ProductCreateDto dto = new ProductCreateDto("Water", new BigDecimal("3.75"));

        Product saved = new Product();
        saved.setId(UUID.randomUUID());
        saved.setName("Water");
        saved.setPrice(new BigDecimal("3.75"));

        when(repo.save(any())).thenReturn(saved);

        // when
        ProductDto result = service.save(dto);

        // then
        verify(repo).save(argThat(p ->
                p.getName().equals("Water") &&
                        p.getPrice().compareTo(new BigDecimal("3.75")) == 0
        ));

        assertThat(result.name()).isEqualTo("Water");
    }
}
