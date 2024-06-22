package com.example.cyber.controller;

import com.example.cyber.controller.ProductController;
import com.example.cyber.model.Product;
import com.example.cyber.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    private ProductService productService;
    private ProductController productController;

    @BeforeEach
    void setUp() {
        productService = Mockito.mock(ProductService.class);
        productController = Mockito.mock(ProductController.class);

    }

    @DisplayName("Test makeNewItem method")
    @Test
    void makeNewItem() {
        Product product = new Product();
        when(productService.create(product)).thenReturn(product);

        ResponseEntity<Product> response = productController.makeNewItem(product);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @DisplayName("Test getProducts method")
    @Test
    void getProducts() {
        List<Product> products = Collections.singletonList(new Product());
        when(productService.getAllProducts()).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.getProducts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @DisplayName("Test getProductsSortedByPrice method")
    @Test
    void getProductsSortedByPrice() {
        boolean ascending = true;
        List<Product> products = Collections.singletonList(new Product());
        when(productService.getAllProductsSortedByPrice(ascending)).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.getProductsSortedByPrice(ascending);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @DisplayName("Test updateProduct method")
    @Test
    void updateProduct() {
        Long productId = 1L;
        Product product = new Product();
        Product updatedProduct = new Product();
        when(productService.updateProduct(productId, updatedProduct)).thenReturn(product);

        ResponseEntity<Product> response = productController.updateProduct(productId, updatedProduct);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @DisplayName("Test deleteProduct method")
    @Test
    void deleteProduct() {
        Long productId = 1L;
        ResponseEntity<Void> response = productController.deleteProduct(productId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(productId);
    }
}

