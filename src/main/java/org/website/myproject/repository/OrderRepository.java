package org.website.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.website.myproject.entity.Order;
import org.website.myproject.enums.OrderStatus;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus status);
}
