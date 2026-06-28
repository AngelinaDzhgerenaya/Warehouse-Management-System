package org.website.myproject.service;

import org.website.myproject.dto.OrderDto;
import org.website.myproject.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto order);

    OrderDto findById(Long id);

    List<OrderDto> findByStatus(OrderStatus status);

    OrderDto confirmOrder(Long orderId);

    OrderDto cancelOrder(Long orderId);
}
