package org.website.myproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.website.myproject.dto.ProductDto;
import org.website.myproject.routes.WarehouseRoutes;
import org.website.myproject.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(WarehouseRoutes.PRODUCTS)
    public ProductDto create(@Valid @RequestBody ProductDto productDto) {
        return productService.create(productDto);
    }

    @GetMapping(WarehouseRoutes.PRODUCTS)
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @GetMapping(WarehouseRoutes.PRODUCTSID)
    public ProductDto findById(@RequestParam Long id) {
        return productService.findById(id);
    }

    @PutMapping(WarehouseRoutes.PRODUCTSID)
    public ProductDto update( @PathVariable Long id,@Valid @RequestBody ProductDto productDto) {
        return productService.update(id, productDto);
    }

    @PostMapping(WarehouseRoutes.PRODUCTSID)
    public String delete(@PathVariable Long id) {
        return productService.delete(id);
    }
}
