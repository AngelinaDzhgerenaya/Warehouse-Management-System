package org.website.myproject.service;

import org.website.myproject.entity.Stock;

public interface StockService {
    Stock getStock(Long productId, Long warehouseId);

    void increase(Long productId, Long warehouseId, Integer quantity);

    void decrease(Long productId, Long warehouseId, Integer quantity);
}
