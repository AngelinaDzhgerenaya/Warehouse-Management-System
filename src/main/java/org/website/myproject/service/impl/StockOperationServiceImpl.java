package org.website.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.website.myproject.entity.StockOperation;
import org.website.myproject.repository.StockOperationRepository;
import org.website.myproject.service.StockOperationService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockOperationServiceImpl implements StockOperationService {
    private final StockOperationRepository stockOperationRepository;


    @Override
    public List<StockOperation> findByProduct(Long productId) {
        return stockOperationRepository.findByProduct(productId);
    }
    @Override
    public StockOperation create(StockOperation operation){
        return stockOperationRepository.save(operation);
    }

}
