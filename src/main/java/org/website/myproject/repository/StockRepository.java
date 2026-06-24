package org.website.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.website.myproject.entity.Product;
import org.website.myproject.entity.Stock;
import org.website.myproject.entity.Warehouse;

import java.util.List;
import java.util.Optional;


public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByProductAndWarehouse(Product product, Warehouse warehouse);
    List<Stock> findByProduct(Product product);
    List<Stock> findByWarehouse(Warehouse warehouse);
}
