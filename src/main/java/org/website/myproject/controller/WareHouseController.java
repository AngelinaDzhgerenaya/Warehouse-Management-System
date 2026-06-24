package org.website.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.website.myproject.dto.WarehouseDto;
import org.website.myproject.routes.WarehouseRoutes;
import org.website.myproject.service.WarehouseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WareHouseController {
    private final WarehouseService warehouseService;

    @PostMapping(WarehouseRoutes.WAREHOUSES)
    public WarehouseDto create(@Valid @RequestBody WarehouseDto warehouseDto) {
        return warehouseService.create(warehouseDto);
    }

    @GetMapping(WarehouseRoutes.WAREHOUSES)
    public List<WarehouseDto> findAll() {
        return warehouseService.findAll();
    }

    @GetMapping(WarehouseRoutes.WAREHOUSESID)
    public WarehouseDto findById(@PathVariable long id) {
        return warehouseService.findById(id);
    }

    @PostMapping(WarehouseRoutes.WAREHOUSESID)
    public String delete(@PathVariable long id) {
        return warehouseService.delete(id);
    }
}
