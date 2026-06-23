package org.website.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.website.myproject.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
