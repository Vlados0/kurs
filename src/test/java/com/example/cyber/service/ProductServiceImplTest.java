package com.example.cyber.service;

import com.example.cyber.exceptions.ProductNotFoundException;
import com.example.cyber.model.Product;
import com.example.cyber.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @DisplayName("Test create method")
    @Test
    void create() {
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productService.create(product);
        assertEquals(product, createdProduct);
    }

    @DisplayName("Test getAllProducts method")
    @Test
    void getAllProducts() {
        Product product = new Product();
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));

        List<Product> products = productService.getAllProducts();
        assertEquals(1, products.size());
        assertEquals(product, products.get(0));
    }

    @DisplayName("Test getProductById method with existing product")
    @Test
    void getProductByIdExisting() {
        Long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Optional<Product> optionalProduct = productService.getProductById(productId);
        assertEquals(Optional.of(product), optionalProduct);
    }

    @DisplayName("Test getProductById method with non-existing product")
    @Test
    void getProductByIdNonExisting() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Optional<Product> optionalProduct = productService.getProductById(productId);
        assertEquals(Optional.empty(), optionalProduct);
    }

    @DisplayName("Test getAllProductsSortedByPrice method")
    @Test
    void getAllProductsSortedByPrice() {
        Product product = new Product();
        when(productRepository.findAll(Sort.by(Sort.Direction.ASC, "price"))).thenReturn(Collections.singletonList(product));

        List<Product> products = productService.getAllProductsSortedByPrice(true);
        assertEquals(1, products.size());
        assertEquals(product, products.get(0));
    }

    @DisplayName("Test updateProduct method with existing product")
    @Test
    void updateProductExisting() {
        Long productId = 1L;
        Product existingProduct = new Product();
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Name");
        updatedProduct.setDescription("Updated Description");
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        Product result = productService.updateProduct(productId, updatedProduct);
        assertEquals(updatedProduct, result);
        assertEquals(updatedProduct.getName(), result.getName());
        assertEquals(updatedProduct.getDescription(), result.getDescription());
    }

    @DisplayName("Test updateProduct method with non-existing product")
    @Test
    void updateProductNonExisting() {
        Long productId = 1L;
        Product updatedProduct = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Product result = productService.updateProduct(productId, updatedProduct);
        assertEquals(null, result);
    }

    @DisplayName("Test deleteProduct method")
    @Test
    void deleteProduct() {
        Long productId = 1L;
        productService.deleteProduct(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }
}
