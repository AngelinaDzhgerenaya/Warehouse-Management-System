package org.website.myproject.mapper;

import org.springframework.stereotype.Component;
import org.website.myproject.dto.OrderDto;

import org.website.myproject.dto.OrderItemDto;
import org.website.myproject.entity.Order;
import org.website.myproject.entity.OrderItem;



@Component
public class OrderMapper {
    public OrderDto toDto(Order order) {

        return new OrderDto(
                order.getId(),
                order.getItems()
                        .stream()
                        .map(this::mapItem)
                        .toList(),
                order.getCreatedAt()
        );
    }
    private OrderItemDto mapItem(OrderItem item) {
        return new OrderItemDto(
                item.getId(),
                item.getProduct().getId(),
                item.getStockId(),
                item.getQuantity(),
                item.getPriceAtMoment(),
                item.getTotalPrice()
        );
    }
}