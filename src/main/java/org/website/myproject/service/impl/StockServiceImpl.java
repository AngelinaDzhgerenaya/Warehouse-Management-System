package org.website.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.website.myproject.dto.StockDto;
import org.website.myproject.entity.Product;
import org.website.myproject.entity.Stock;
import org.website.myproject.entity.Warehouse;
import org.website.myproject.enums.OperationType;
import org.website.myproject.exceptions.ConflictException;
import org.website.myproject.exceptions.NotFoundException;
import org.website.myproject.mapper.StockMapper;
import org.website.myproject.repository.ProductRepository;
import org.website.myproject.repository.StockRepository;
import org.website.myproject.repository.WarehouseRepository;
import org.website.myproject.service.StockService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    private final StockOperationServiceImpl stockOperationService;

    private final ProductRepository productRepository;

    private final WarehouseRepository warehouseRepository;

    @Override
    public StockDto getStock(Long productId, Long warehouseId){
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new NotFoundException("Такого продукта не существует"));
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow(() ->
                new NotFoundException("Такого склада не существует"));
        Stock stock = stockRepository.findByProductAndWarehouse(product, warehouse).orElseThrow(() ->
                new NotFoundException("Невернае данные хранилища"));
        return stockMapper.toDto(stock);
    }

    @Override
    @Transactional
    public StockDto increase(StockDto stockDto) {
        Product product = productRepository.findById(stockDto.getProductId()).orElseThrow(() ->
                new NotFoundException("Такого продукта не существует"));
        Warehouse warehouse = warehouseRepository.findById(stockDto.getWarehouseId()).orElseThrow(() ->
                new NotFoundException("Такого склада не существует"));
        Optional<Stock> stockOptional = stockRepository.findByProductAndWarehouse(product, warehouse);
        if (stockOptional.isEmpty()) {
            Stock newStock = Stock.builder()
                    .product(product)
                    .warehouse(warehouse)
                    .quantity(stockDto.getQuantity())
                    .reservedQuantity(0)
                    .build();
            Stock saved = stockRepository.save(newStock);
            stockOperationService.create(
                    saved,
                    OperationType.IMPORT,
                    stockDto.getQuantity()
            );
            return stockMapper.toDto(saved);
        }
        Stock stock = stockOptional.orElseThrow();
        stock.setQuantity(stock.getQuantity() + stockDto.getQuantity());
        Stock saved = stockRepository.save(stock);
        stockOperationService.create(
                saved,
                OperationType.IMPORT,
                stockDto.getQuantity()
        );
        return stockMapper.toDto(saved);
    }
    @Override
    @Transactional
    public StockDto decrease(StockDto stockDto){
        Product product = productRepository.findById(stockDto.getProductId()).orElseThrow(() ->
                new NotFoundException("Такого продукта не существует"));
        Warehouse warehouse = warehouseRepository.findById(stockDto.getWarehouseId()).orElseThrow(() ->
                new NotFoundException("Такого склада не существует"));
        Stock stock = stockRepository.findByProductAndWarehouse(product, warehouse).orElseThrow(() ->
                new NotFoundException("Такой продукт не хранится на этом складе"));
        if (stock.getQuantity() < stockDto.getQuantity()){
            throw new ConflictException("На складе не хватает товара");
        }
        stock.setQuantity(stock.getQuantity() - stockDto.getQuantity());
        Stock saved = stockRepository.save(stock);
        stockOperationService.create(
                saved,
                OperationType.SALE,
                stockDto.getQuantity()
        );
        return stockMapper.toDto(saved);
    }


    @Override
    @Transactional
    public Long orderReserve(Long productId, Integer reservedQuantity){
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new NotFoundException("Такого продукта не существует"));

        List<Stock> stocks = stockRepository.findByProduct(product);

        Stock stock = stocks.stream()
                .filter(s -> s.getQuantity() >= reservedQuantity)
                .findFirst()
                .orElseThrow(() ->
                        new NotFoundException("Нет склада с достаточным количеством товара"));

        stock.setQuantity(stock.getQuantity() - reservedQuantity);
        stock.setReservedQuantity(stock.getReservedQuantity() + reservedQuantity);

        Stock saved = stockRepository.save(stock);

        stockOperationService.create(
                saved,
                OperationType.RESERVE,
                reservedQuantity
        );
        return saved.getId();
    }
}
