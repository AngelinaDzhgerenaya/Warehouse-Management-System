package org.website.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.website.myproject.entity.Stock;
import org.website.myproject.repository.StockRepository;
import org.website.myproject.service.StockService;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    @Override
    public Stock getStock(Long productId, Long warehouseId){
        return stockRepository.findByProductAndWarehouse(productId, warehouseId);
    }

    @Override
    public void increase(Long productId, Long warehouseId, Integer quantity){
        Stock stock = getStock(productId, warehouseId);
        stock.setQuantity(stock.getQuantity() + quantity);
        stockRepository.save(stock);
    }
    @Override
    public void decrease(Long productId, Long warehouseId, Integer quantity){
        Stock stock = getStock(productId, warehouseId);
        stock.setQuantity(stock.getQuantity() - quantity);
        stockRepository.save(stock);
    }
}
