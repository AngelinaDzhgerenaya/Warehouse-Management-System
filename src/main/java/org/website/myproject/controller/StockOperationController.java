package org.website.myproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.website.myproject.dto.StockOperationDto;
import org.website.myproject.routes.WarehouseRoutes;
import org.website.myproject.service.StockOperationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockOperationController {
    private final StockOperationService stockOperationService;

    @GetMapping(WarehouseRoutes.STOCKOPERATIONSPRODUCT)
    public List<StockOperationDto> stockOperationsProduct(@PathVariable Long id) {
        return stockOperationService.findByProduct(id);
    }

    @GetMapping(WarehouseRoutes.STOCKOPERATIONSWAREHOUSE)
    public List<StockOperationDto> stockOperationsWarehouse(@PathVariable Long id) {
        return stockOperationService.findByWarehouse(id);
    }
}
