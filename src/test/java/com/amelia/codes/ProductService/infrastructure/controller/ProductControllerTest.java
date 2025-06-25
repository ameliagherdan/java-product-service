package com.amelia.codes.ProductService.infrastructure.controller;

import com.amelia.codes.ProductService.application.dto.ProductCreateDto;
import com.amelia.codes.ProductService.application.dto.ProductDto;
import com.amelia.codes.ProductService.application.service.ProductService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("removal")
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    void shouldReturnAllProducts() throws Exception {
        UUID id = UUID.randomUUID();
        ProductDto dto = new ProductDto(id, "Mocked", new BigDecimal("1.23"));
        when(productService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(id.toString()))
                .andExpect(jsonPath("$[0].name").value("Mocked"))
                .andExpect(jsonPath("$[0].price").value(1.23));
    }

    @Test
    void shouldCreateProduct() throws Exception {
        UUID id = UUID.randomUUID();
        ProductCreateDto createDto = new ProductCreateDto("New", new BigDecimal("5.99"));
        ProductDto saved = new ProductDto(id, createDto.name(), createDto.price());

        when(productService.save(any())).thenReturn(saved);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value("New"))
                .andExpect(jsonPath("$.price").value(5.99));
    }

    @Test
    void shouldReturnBadRequestWhenNameIsMissing() throws Exception {
        ProductCreateDto invalid = new ProductCreateDto("", new BigDecimal("10"));

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenPriceIsNegative() throws Exception {
        ProductCreateDto invalid = new ProductCreateDto("Invalid", new BigDecimal("-2.00"));

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenBodyIsEmpty() throws Exception {
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        UUID id = UUID.randomUUID();
        doNothing().when(productService).delete(id);

        mockMvc.perform(delete("/api/products/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenIdIsInvalidUUID() throws Exception {
        mockMvc.perform(delete("/api/products/invalid-uuid"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnMethodNotAllowedForPut() throws Exception {
        mockMvc.perform(put("/api/products"))
                .andExpect(status().isMethodNotAllowed());
    }
}