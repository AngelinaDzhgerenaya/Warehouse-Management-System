package org.website.myproject.mapper;

import org.springframework.stereotype.Component;
import org.website.myproject.dto.CategoryDto;
import org.website.myproject.entity.Category;

@Component
public class CategoryMapper {
    public CategoryDto toDto(Category category) {

        return new CategoryDto(
                category.getId(),
                category.getName()
        );

    }

    public Category toEntity(CategoryDto dto) {

        Category category = new Category();

        category.setId(dto.getId());
        category.setName(dto.getName());

        return category;
    }
}
