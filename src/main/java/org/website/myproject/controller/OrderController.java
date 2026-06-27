package org.website.myproject.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.website.myproject.dto.OrderDto;
import org.website.myproject.routes.WarehouseRoutes;
import org.website.myproject.service.OrderService;

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
}
