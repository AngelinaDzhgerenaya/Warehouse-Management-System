package org.website.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.website.myproject.entity.Warehouse;
import org.website.myproject.repository.WarehouseRepository;
import org.website.myproject.service.WarehouseService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;

    @Override
    public Warehouse create(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse findById(Long id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }
}
