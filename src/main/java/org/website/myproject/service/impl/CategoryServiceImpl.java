package org.website.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.website.myproject.dto.CategoryDto;
import org.website.myproject.entity.Category;
import org.website.myproject.entity.Product;
import org.website.myproject.exceptions.ConflictException;
import org.website.myproject.exceptions.NotFoundException;
import org.website.myproject.mapper.CategoryMapper;
import org.website.myproject.repository.CategoryRepository;
import org.website.myproject.repository.ProductRepository;
import org.website.myproject.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    private final ProductRepository productRepository;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = categoryRepository.findByName(categoryDto.getName());
        if (category != null) {
            throw new ConflictException("Такая категория уже существует");
        }

        Category categoryNew = categoryMapper.toEntity(categoryDto);

        Category saved = categoryRepository.save(categoryNew);
        return categoryMapper.toDto(saved);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Такой категории не существует"));
        return categoryMapper.toDto(category);
    }

    @Override
    public String delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Такой категории не существует"));
        List<Product> products = productRepository.findByCategory(category);
        if (!products.isEmpty()) {
            throw new ConflictException("К этой категории привязаны продукты");
        }
        categoryRepository.deleteById(id);
        return "Категория удалена";
    }
}
