package org.website.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.website.myproject.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
