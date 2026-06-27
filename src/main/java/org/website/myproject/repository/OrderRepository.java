package org.website.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.website.myproject.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
