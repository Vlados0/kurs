package com.example.cyber.controller;

import com.example.cyber.api.VisitorClient;
import com.example.cyber.api.responses.Visitor;
import com.example.cyber.model.Product;
import com.example.cyber.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {

    private final VisitorClient visitorClient;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> makeNewItem(@RequestBody Product product) {
        return ResponseEntity.ok(productService.create(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        try {
            visitorClient.registerVisitor(new Visitor(
                    null,
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(),
                    0,
                    null,
                    null
            ));
        } catch (Exception e) {
            log.error("Cannot get visitor service: {}", e.getMessage(), e);
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/sorted-by-price")
    public ResponseEntity<List<Product>> getProductsSortedByPrice(@RequestParam boolean ascending) {
        return ResponseEntity.ok(productService.getAllProductsSortedByPrice(ascending));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        return ResponseEntity.ok(productService.updateProduct(productId, updatedProduct));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

}
