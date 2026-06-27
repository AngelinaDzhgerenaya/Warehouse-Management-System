package org.website.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.website.myproject.dto.StockDto;
import org.website.myproject.routes.WarehouseRoutes;
import org.website.myproject.service.StockService;

@RestController
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping(WarehouseRoutes.STOCKS)
    public StockDto stocks(@RequestParam Long productId, @RequestParam Long warehouseId) {
        return stockService.getStock(productId, warehouseId);
    }

    @PostMapping(WarehouseRoutes.STOCKSINCREASE)
    public StockDto increase(@Valid @RequestBody StockDto stockDto) {
        return stockService.increase(stockDto);
    }

    @PostMapping(WarehouseRoutes.STOCKSDECREASE)
    public StockDto decrease(@Valid @RequestBody StockDto stockDto) {
        return stockService.decrease(stockDto);
    }

}
