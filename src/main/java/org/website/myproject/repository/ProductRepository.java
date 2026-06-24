package org.website.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.website.myproject.entity.Category;
import org.website.myproject.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}
