package org.website.myproject.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.website.myproject.dto.OrderDto;
import org.website.myproject.dto.OrderItemDto;
import org.website.myproject.dto.StockDto;
import org.website.myproject.entity.*;
import org.website.myproject.enums.OrderStatus;
import org.website.myproject.exceptions.NotFoundException;
import org.website.myproject.mapper.OrderMapper;
import org.website.myproject.repository.*;
import org.website.myproject.service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    private final ProductRepository productRepository;

    private final StockServiceImpl stockService;



    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {

        Order order = Order.builder()
                .status(OrderStatus.NEW)
                .build();
        List<OrderItem> items = new ArrayList<>();

        for (OrderItemDto itemDto : orderDto.getItems()) {

            Long stockId = stockService.orderReserve(itemDto.getProductId(), itemDto.getQuantity());

            Product product = productRepository.findById(itemDto.getProductId()).orElseThrow(() ->
                    new NotFoundException("Такого продукта не существует"));

            BigDecimal price = product.getPrice();
            BigDecimal total = price.multiply(BigDecimal.valueOf(itemDto.getQuantity()));

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemDto.getQuantity())
                    .stockId(stockId)
                    .priceAtMoment(product.getPrice())
                    .totalPrice(total)
                    .build();

            items.add(orderItem);
        }
        order.setItems(items);

        Order saved = orderRepository.save(order);

        return orderMapper.toDto(saved);
    }

    @Override
    public OrderDto findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Такого заказа не существует"));

        return orderMapper.toDto(order);
    }
}
