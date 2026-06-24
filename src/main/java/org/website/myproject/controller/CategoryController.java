package org.website.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.website.myproject.dto.CategoryDto;
import org.website.myproject.routes.WarehouseRoutes;
import org.website.myproject.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(WarehouseRoutes.CATEGORIES)
    public CategoryDto create(@Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.create(categoryDto);
    }

    @GetMapping(WarehouseRoutes.CATEGORIES)
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @GetMapping(WarehouseRoutes.CATEGORIESID)
    public CategoryDto findById( @RequestParam Long id) {
        return categoryService.findById(id);
    }

    @PostMapping(WarehouseRoutes.CATEGORIESID)
    public String delete(@RequestBody Long id) {
        return categoryService.delete(id);
    }
}
