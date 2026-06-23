package org.website.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.website.myproject.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
