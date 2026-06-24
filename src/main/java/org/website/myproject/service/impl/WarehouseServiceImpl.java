package org.website.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.website.myproject.dto.WarehouseDto;
import org.website.myproject.entity.Stock;
import org.website.myproject.entity.Warehouse;
import org.website.myproject.exceptions.ConflictException;
import org.website.myproject.exceptions.NotFoundException;
import org.website.myproject.mapper.WarehouseMapper;
import org.website.myproject.repository.StockRepository;
import org.website.myproject.repository.WarehouseRepository;
import org.website.myproject.service.StockService;
import org.website.myproject.service.WarehouseService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    private final StockRepository stockRepository;

    @Override
    public WarehouseDto create(WarehouseDto warehouseDto) {
        Warehouse warehouse = Warehouse.builder()
                .name(warehouseDto.getName())
                .address(warehouseDto.getAddress())
                .build();
        Warehouse saved = warehouseRepository.save(warehouse);
        return warehouseMapper.toDto(saved);
    }

    @Override
    public WarehouseDto findById(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Такого склада не существует"));
        return warehouseMapper.toDto(warehouse);
    }

    @Override
    public List<WarehouseDto> findAll() {

        return warehouseRepository.findAll()
                .stream()
                .map(warehouseMapper::toDto)
                .toList();
    }

    @Override
    public String delete(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Такого склада не существует"));
        List<Stock> stocks = stockRepository.findByWarehouse(warehouse);
        if (!stocks.isEmpty()) {
            throw new ConflictException("На этом складе ещё есть продукты");
        }
        warehouseRepository.deleteById(id);
        return "Склад удалён";
    }
}
