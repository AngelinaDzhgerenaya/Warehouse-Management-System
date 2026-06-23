package org.website.myproject.service;

import org.website.myproject.entity.StockOperation;

import java.util.List;

public interface StockOperationService {
    StockOperation create(StockOperation operation);

    List<StockOperation> findByProduct(Long productId);
}
