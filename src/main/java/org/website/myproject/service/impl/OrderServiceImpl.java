package org.website.myproject.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.website.myproject.dto.OrderDto;
import org.website.myproject.dto.OrderItemDto;
import org.website.myproject.entity.*;
import org.website.myproject.enums.OrderStatus;
import org.website.myproject.exceptions.ConflictException;
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
    private final StockRepository stockRepository;



    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {

        Order order = Order.builder()
                .status(OrderStatus.NEW)
                .build();
        List<OrderItem> items = new ArrayList<>();

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemDto itemDto : orderDto.getItems()) {

            Long stockId = stockService.orderReserve(itemDto.getProductId(), itemDto.getQuantity());
            Stock stock = stockRepository.findById(stockId).orElseThrow();

            Product product = productRepository.findById(itemDto.getProductId()).orElseThrow(() ->
                    new NotFoundException("Такого продукта не существует"));

            BigDecimal price = product.getPrice();
            BigDecimal total = price.multiply(BigDecimal.valueOf(itemDto.getQuantity()));

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemDto.getQuantity())
                    .stock(stock)
                    .priceAtMoment(product.getPrice())
                    .totalPrice(total)
                    .build();

            items.add(orderItem);
            totalAmount = totalAmount.add(total);
        }
        order.setItems(items);
        order.setTotalAmount(totalAmount);

        Order saved = orderRepository.save(order);
        return orderMapper.toDto(saved);
    }

    @Override
    public OrderDto findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Такого заказа не существует"));

        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDto> findByStatus(OrderStatus status) {
        List<Order> order = orderRepository.findByStatus(status);
        if (order.isEmpty()) {
            throw new NotFoundException("Заказов с таким статусом нет");
        }
        return order.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderDto confirmOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new NotFoundException("Заказ не найден"));

        if(order.getStatus()!=OrderStatus.NEW) {
            throw new ConflictException("Этот заказ уже обработан");
        }

        for (OrderItem item : order.getItems()) {
            stockService.orderConfirm(item.getStock().getId(), item.getQuantity());
        }

        order.setStatus(OrderStatus.CONFIRMED);

        Order saved = orderRepository.save(order);
        return orderMapper.toDto(saved);
    }

    @Override
    @Transactional
    public OrderDto cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new NotFoundException("Заказ не найден"));

        if(order.getStatus()!=OrderStatus.NEW) {
            throw new ConflictException("Этот заказ уже обработан");
        }
        for (OrderItem item : order.getItems()) {
            stockService.orderCancel(item.getStock().getId(), item.getQuantity());
        }

        order.setStatus(OrderStatus.CANCELED);

        Order saved = orderRepository.save(order);
        return orderMapper.toDto(saved);
    }

}
