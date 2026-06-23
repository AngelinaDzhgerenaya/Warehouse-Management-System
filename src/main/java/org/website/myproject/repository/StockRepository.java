package org.website.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.website.myproject.entity.Stock;


public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByProductAndWarehouse(Long productId, Long warehouseId);
}
