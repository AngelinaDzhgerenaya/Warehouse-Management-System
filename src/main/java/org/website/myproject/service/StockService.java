package org.website.myproject.service;

import org.website.myproject.dto.StockDto;


public interface StockService {
    StockDto getStock(Long productId, Long warehouseId);

    StockDto increase(StockDto stockDto);

    StockDto decrease(StockDto stockDto);
}
