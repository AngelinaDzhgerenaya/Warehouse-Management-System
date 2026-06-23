package org.website.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.website.myproject.entity.StockOperation;

import java.util.List;

public interface StockOperationRepository extends JpaRepository<StockOperation, Long> {
    List<StockOperation> findByProduct(Long ProductId);
}
