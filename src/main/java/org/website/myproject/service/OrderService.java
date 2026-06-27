package org.website.myproject.service;

import org.website.myproject.dto.OrderDto;

public interface OrderService {
    OrderDto createOrder(OrderDto order);

    OrderDto findById(Long id);
}
