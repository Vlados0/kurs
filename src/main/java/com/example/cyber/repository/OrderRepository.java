package com.example.cyber.repository;

import com.example.cyber.model.Order;
import com.example.cyber.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long>, JpaRepository<Order, Long> {
    List<Order> findAllByProduct(Product product);

    List<Order> findAllByOrderByOrderDateAsc();

    List<Order> findAllByOrderByOrderDateDesc();
}
