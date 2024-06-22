package com.example.cyber.service;

import com.example.cyber.controller.requests.OrderRequests;
import com.example.cyber.exceptions.ProductNotFoundException;
import com.example.cyber.model.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    Order placeNewOrder(OrderRequests order) throws ProductNotFoundException;
    Page<Order> getAllOrders(int page, boolean sortByDate);
    List<Order> getOrdersByItem(Long id) throws ProductNotFoundException;
    List<Order> getAllOrdersSortedByDate(boolean ascending);

    void deleteOrder(Long id);

    Order updateOrder(Long id, Order updatedOrder);
}
