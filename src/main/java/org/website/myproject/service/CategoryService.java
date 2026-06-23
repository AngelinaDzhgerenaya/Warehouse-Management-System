package org.website.myproject.service;

import org.website.myproject.dto.CategoryDto;


import java.util.List;

public interface CategoryService {

    CategoryDto create(CategoryDto categoryDto);
    List<CategoryDto> findAll();
    CategoryDto findById(Long id);
    void delete(Long id);

}
