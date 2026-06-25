package org.website.myproject.service;

import org.website.myproject.dto.StockOperationDto;
import org.website.myproject.entity.Stock;
import org.website.myproject.enums.OperationType;

import java.util.List;

public interface StockOperationService {
    void create(Stock stock, OperationType type, Integer quantity);

    List<StockOperationDto> findByProduct(Long id);
    List<StockOperationDto> findByWarehouse(Long id);
}
