package com.example.cyber.controller;

import com.example.cyber.controller.requests.OrderRequests;
import com.example.cyber.exceptions.ProductNotFoundException;
import com.example.cyber.model.Order;
import com.example.cyber.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    private OrderService orderService;
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        orderService = Mockito.mock(OrderService.class);
        orderController = new OrderController(orderService);
    }

    @DisplayName("Test placeOrder method")
    @Test
    void placeOrder() throws ProductNotFoundException {
        OrderRequests orderRequests = new OrderRequests();
        Order order = new Order();
        when(orderService.placeNewOrder(orderRequests)).thenReturn(order);

        ResponseEntity<Order> response = orderController.placeOrder(orderRequests);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());
    }

    @DisplayName("Test getAllOrders method")
    @Test
    void getAllOrders() {
        int page = 0;
        boolean sortByDate = true;
        Page<Order> orderPage = Mockito.mock(Page.class);
        when(orderService.getAllOrders(page, sortByDate)).thenReturn(orderPage);

        ResponseEntity<Page<Order>> response = orderController.getAllOrders(page, sortByDate);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderPage, response.getBody());
    }

    @DisplayName("Test getOrderByItemId method")
    @Test
    void getOrderByItemId() throws ProductNotFoundException {
        Long id = 1L;
        List<Order> orders = Collections.singletonList(new Order());
        when(orderService.getOrdersByItem(id)).thenReturn(orders);

        ResponseEntity<List<Order>> response = orderController.getOrderByItemId(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
    }

    @DisplayName("Test getAllOrdersSortedByDate method")
    @Test
    void getAllOrdersSortedByDate() {
        boolean ascending = true;
        List<Order> orders = Collections.singletonList(new Order());
        when(orderService.getAllOrdersSortedByDate(ascending)).thenReturn(orders);

        ResponseEntity<List<Order>> response = orderController.getAllOrdersSortedByDate(ascending);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
    }

    @DisplayName("Test deleteOrder method")
    @Test
    void deleteOrder() {
        Long id = 1L;
        ResponseEntity<Void> response = orderController.deleteOrder(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(orderService, times(1)).deleteOrder(id);
    }

    @DisplayName("Test updateOrder method with existing order")
    @Test
    void updateOrderExisting() {
        Long id = 1L;
        Order order = new Order();
        Order updatedOrder = new Order();
        when(orderService.updateOrder(id, updatedOrder)).thenReturn(order);

        ResponseEntity<Order> response = orderController.updateOrder(id, updatedOrder);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());
    }

    @DisplayName("Test updateOrder method with non-existing order")
    @Test
    void updateOrderNonExisting() {
        Long id = 1L;
        Order updatedOrder = new Order();
        when(orderService.updateOrder(id, updatedOrder)).thenReturn(null);

        ResponseEntity<Order> response = orderController.updateOrder(id, updatedOrder);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
