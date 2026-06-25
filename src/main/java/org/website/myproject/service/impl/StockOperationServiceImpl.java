package org.website.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.website.myproject.dto.StockOperationDto;
import org.website.myproject.entity.Product;
import org.website.myproject.entity.Stock;
import org.website.myproject.entity.StockOperation;
import org.website.myproject.entity.Warehouse;
import org.website.myproject.enums.OperationType;
import org.website.myproject.exceptions.NotFoundException;
import org.website.myproject.mapper.StockOperationMapper;
import org.website.myproject.repository.ProductRepository;
import org.website.myproject.repository.StockOperationRepository;
import org.website.myproject.repository.WarehouseRepository;
import org.website.myproject.service.StockOperationService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockOperationServiceImpl implements StockOperationService {
    private final StockOperationRepository stockOperationRepository;
    private final StockOperationMapper stockOperationMapper;

    private final ProductRepository productRepository;

    private final WarehouseRepository warehouseRepository;



    @Override
    public List<StockOperationDto> findByProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Такого продукта не существует"));
        return stockOperationRepository.findByProduct(product)
                .stream()
                .map(stockOperationMapper::toDto)
                .toList();
    }

    @Override
    public List<StockOperationDto> findByWarehouse(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Такого склада не существует"));
        return stockOperationRepository.findByWarehouse(warehouse)
                .stream()
                .map(stockOperationMapper::toDto)
                .toList();
    }

    @Override
    public void create(Stock stock, OperationType type, Integer quantity){
        StockOperation newStockOperation = StockOperation.builder()
                .product(stock.getProduct())
                .warehouse(stock.getWarehouse())
                .type(type)
                .quantity(quantity)
                .build();
        stockOperationRepository.save(newStockOperation);
    }

}
