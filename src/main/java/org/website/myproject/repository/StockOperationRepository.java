package org.website.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.website.myproject.entity.Product;
import org.website.myproject.entity.StockOperation;
import org.website.myproject.entity.Warehouse;

import java.util.List;

public interface StockOperationRepository extends JpaRepository<StockOperation, Long> {
    List<StockOperation> findByProduct(Product product);

    List<StockOperation> findByWarehouse(Warehouse warehouse);
}
