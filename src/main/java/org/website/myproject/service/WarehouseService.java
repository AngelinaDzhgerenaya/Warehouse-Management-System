package org.website.myproject.service;

import org.website.myproject.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
    Warehouse create(Warehouse warehouse);

    List<Warehouse> findAll();

    Warehouse findById(Long id);
}
