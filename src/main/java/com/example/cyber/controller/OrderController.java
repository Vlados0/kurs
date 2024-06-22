package com.example.cyber.controller;

import com.example.cyber.controller.requests.OrderRequests;
import com.example.cyber.exceptions.ProductNotFoundException;
import com.example.cyber.model.Order;
import com.example.cyber.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequests orderRequest) throws ProductNotFoundException {
        return ResponseEntity.ok(orderService.placeNewOrder(orderRequest));
    }

    @GetMapping
    public ResponseEntity<Page<Order>> getAllOrders(
            @RequestParam int page,
            @RequestParam boolean sortByDate
    ) {
        return ResponseEntity.ok(orderService.getAllOrders(page, sortByDate));
    }

    @GetMapping("/findById")
    public ResponseEntity<List<Order>> getOrderByItemId(@RequestParam("id") Long id) throws ProductNotFoundException {
        return ResponseEntity.ok(orderService.getOrdersByItem(id));
    }

    @GetMapping("/sorted-by-date")
    public ResponseEntity<List<Order>> getAllOrdersSortedByDate(@RequestParam boolean ascending) {
        return ResponseEntity.ok(orderService.getAllOrdersSortedByDate(ascending));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        Order order = orderService.updateOrder(id, updatedOrder);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }


}
