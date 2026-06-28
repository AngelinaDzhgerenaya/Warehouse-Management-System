package org.website.myproject.service;

import org.website.myproject.dto.StockDto;
import org.website.myproject.entity.Warehouse;


public interface StockService {
    StockDto getStock(Long productId, Long warehouseId);

    StockDto increase(StockDto stockDto);

    StockDto decrease(StockDto stockDto);

    Long orderReserve(Long productId, Integer reservedQuantity);

    void orderConfirm(Long stockId, Integer reservedQuantity);

    void orderCancel(Long stockId, Integer reservedQuantity);
}
