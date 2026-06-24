package org.website.myproject.service;

import org.website.myproject.dto.WarehouseDto;


import java.util.List;

public interface WarehouseService {
    WarehouseDto create(WarehouseDto warehouseDto);

    List<WarehouseDto> findAll();

    WarehouseDto findById(Long id);

    String delete(Long id);
}
