package com.example.cyber.service;

import com.example.cyber.controller.requests.OrderRequests;
import com.example.cyber.exceptions.ProductNotFoundException;
import com.example.cyber.model.Order;
import com.example.cyber.model.Product;
import com.example.cyber.model.User;
import com.example.cyber.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    private OrderRepository orderRepository;
    private ProductService productService;
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
        productService = Mockito.mock(ProductService.class);
        orderService = new OrderServiceImpl(orderRepository, productService);
    }

    @DisplayName("Тест метода placeNewOrder с существующим продуктом")
    @Test
    void placeNewOrderExistingProduct() throws ProductNotFoundException {
        Long productId = 1L;
        String address = "Тестовый адрес";
        Product product = new Product();
        User user = new User();
        Order order = new Order(null, product, user, address, new Date());
        OrderServiceImpl spyOrderService = Mockito.spy(orderService);
        Mockito.when(productService.getProductById(productId)).thenReturn(Optional.of(product));
        Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);
        Mockito.doReturn(order).when(spyOrderService).placeNewOrder(Mockito.any());

        Order result = spyOrderService.placeNewOrder(new OrderRequests(productId, address));
        assertEquals(order, result);
        verify(orderRepository, times(1)).save(order);
    }

    @DisplayName("Тест метода placeNewOrder с несуществующим продуктом")
    @Test
    void placeNewOrderNonExistingProduct() {
        Long productId = 1L;
        String address = "Тестовый адрес";
        OrderServiceImpl spyOrderService = Mockito.spy(orderService);
        Mockito.when(productService.getProductById(productId)).thenReturn(Optional.empty());

        try{
            spyOrderService.placeNewOrder(new OrderRequests(productId, address));
        } catch (ProductNotFoundException e) {
            assertEquals("Product with id " + productId + " not found", e.getMessage());
        }
    }
}
