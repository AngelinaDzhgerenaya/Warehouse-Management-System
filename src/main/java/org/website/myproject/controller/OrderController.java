package org.website.myproject.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.website.myproject.dto.OrderDto;
import org.website.myproject.enums.OrderStatus;
import org.website.myproject.routes.WarehouseRoutes;
import org.website.myproject.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping(WarehouseRoutes.ORDERSID)
    public OrderDto findById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PostMapping(WarehouseRoutes.ORDERS)
    public OrderDto save(@Valid @RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @GetMapping(WarehouseRoutes.ORDERS)
    public List<OrderDto> findById(@RequestParam OrderStatus status) {
        return orderService.findByStatus(status);
    }

    @PostMapping(WarehouseRoutes.ORDERCONFIRM)
    public OrderDto confirmOrder(@PathVariable Long id) {
        return orderService.confirmOrder(id);
    }

    @PostMapping(WarehouseRoutes.ORDERCANCEL)
    public OrderDto cancelOrder(@PathVariable Long id) {
        return orderService.cancelOrder(id);
    }

}
